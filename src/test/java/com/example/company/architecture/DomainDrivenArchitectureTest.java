package com.example.company.architecture;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

/**
 * Architecture tests to enforce domain-driven design principles.
 * These tests ensure the codebase maintains proper layering and naming conventions.
 */
class DomainDrivenArchitectureTest {

    private static JavaClasses importedClasses;

    @BeforeAll
    static void setup() {
        importedClasses = new ClassFileImporter()
                .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
                .importPackages("com.example.company");
    }

    @Test
    void controllers_should_be_in_controller_package() {
        classes()
                .that().haveSimpleNameEndingWith("Controller")
                .should().resideInAPackage("..controller..")
                .because("Controllers should be organized in controller packages")
                .check(importedClasses);
    }

    @Test
    void services_should_be_in_service_package() {
        classes()
                .that().haveSimpleNameEndingWith("Service")
                .should().resideInAPackage("..service..")
                .because("Services should be organized in service packages")
                .check(importedClasses);
    }

    @Test
    void repositories_should_be_in_repository_package() {
        classes()
                .that().haveSimpleNameEndingWith("Repository")
                .should().resideInAPackage("..repository..")
                .because("Repositories should be organized in repository packages")
                .check(importedClasses);
    }

    @Test
    void entities_should_be_in_model_package() {
        classes()
                .that().areAnnotatedWith(jakarta.persistence.Entity.class)
                .should().resideInAPackage("..model..")
                .because("Entities should be organized in model packages")
                .check(importedClasses);
    }

    @Test
    void controllers_should_depend_on_services() {
        classes()
                .that().haveSimpleNameEndingWith("Controller")
                .should().dependOnClassesThat().haveSimpleNameEndingWith("Service")
                .because("Controllers should use Services for business logic")
                .check(importedClasses);
    }

    @Test
    void services_should_not_depend_on_controllers() {
        classes()
                .that().haveSimpleNameEndingWith("Service")
                .should().onlyDependOnClassesThat()
                .resideOutsideOfPackage("..controller..")
                .because("Services should not depend on Controllers")
                .check(importedClasses);
    }

    @Test
    void repositories_should_be_interfaces() {
        classes()
                .that().haveSimpleNameEndingWith("Repository")
                .and().resideInAPackage("..repository..")
                .should().beInterfaces()
                .because("Repositories should be interfaces for Spring Data JPA")
                .check(importedClasses);
    }
}
