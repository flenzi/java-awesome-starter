# Java Awesome Starter

A production-ready Spring Boot starter template featuring domain-driven design, comprehensive testing, automated security scanning, and enterprise-grade best practices.

## Features

- **Spring Boot 3.3.5** with Java 21 LTS
- **Domain-Driven Design** architecture with clear layer separation
- **Comprehensive Testing** with JUnit 5, Mockito, and ArchUnit for architecture validation
- **Code Coverage** enforced at 80% minimum with JaCoCo
- **OpenAPI Documentation** with Swagger UI
- **Database Migration** using Liquibase
- **Multi-Profile Configuration** (dev, test, prod)
- **Docker Support** with multi-stage builds and security best practices
- **CI/CD Pipeline** with GitHub Actions including Trivy security scanning
- **Code Quality Tools** and standardized project structure

## Tech Stack

### Core
- **Java 21** - Latest LTS version
- **Spring Boot 3.3.5** - Application framework
- **Gradle 8.11.1** - Build tool with Kotlin DSL

### Persistence
- **Spring Data JPA** - Data access layer
- **Liquibase** - Database migrations
- **PostgreSQL** - Production database
- **H2** - Development/testing database

### Testing & Quality
- **JUnit 5** - Testing framework
- **Mockito** - Mocking framework
- **ArchUnit** - Architecture testing
- **JaCoCo** - Code coverage (60% minimum, configurable)

### Documentation
- **SpringDoc OpenAPI** - API documentation and Swagger UI

### DevOps
- **Docker** - Containerization
- **Docker Compose** - Local development environment
- **GitHub Actions** - CI/CD pipeline
- **Trivy** - Container vulnerability scanning

### Development Tools
- **Lombok** - Reduces boilerplate code
- **Spring Boot DevTools** - Hot reload during development

## Project Structure

```
java-awesome-starter/
├── src/
│   ├── main/
│   │   ├── java/com/example/company/
│   │   │   ├── domain/              # Domain-driven modules
│   │   │   │   ├── user/            # User domain
│   │   │   │   │   ├── controller/  # REST controllers
│   │   │   │   │   ├── service/     # Business logic
│   │   │   │   │   ├── repository/  # Data access
│   │   │   │   │   └── model/       # Domain entities
│   │   │   │   └── product/         # Product domain
│   │   │   │       ├── controller/
│   │   │   │       ├── service/
│   │   │   │       ├── repository/
│   │   │   │       └── model/
│   │   │   ├── common/              # Shared components
│   │   │   │   ├── config/          # Configuration classes
│   │   │   │   ├── exception/       # Exception handling
│   │   │   │   └── util/            # Utility classes
│   │   │   └── Application.java     # Main entry point
│   │   └── resources/
│   │       ├── application.yml          # Base configuration
│   │       ├── application-dev.yml      # Development config
│   │       ├── application-test.yml     # Test config
│   │       ├── application-prod.yml     # Production config
│   │       └── db/changelog/            # Liquibase migrations
│   └── test/
│       └── java/com/example/company/
│           ├── domain/              # Domain tests
│           └── architecture/        # ArchUnit tests
├── .github/workflows/               # CI/CD pipelines
├── Dockerfile                       # Multi-stage Docker build
├── docker-compose.yml              # Local development setup
├── build.gradle.kts                # Build configuration
└── README.md                       # This file
```

## Getting Started

### Prerequisites

- **JDK 21** or higher
- **Docker & Docker Compose** (optional, for containerized development)

### Quick Start

1. **Clone the repository**
   ```bash
   git clone https://github.com/yourusername/java-awesome-starter.git
   cd java-awesome-starter
   ```

2. **Build the project**
   ```bash
   ./gradlew build
   ```

3. **Run the application**
   ```bash
   ./gradlew bootRun
   ```

4. **Access the application**
   - API: http://localhost:8080
   - Swagger UI: http://localhost:8080/swagger-ui.html
   - H2 Console: http://localhost:8080/h2-console (dev profile)

### Docker Deployment

**Development with Docker Compose:**
```bash
docker-compose up
```

**Build Docker image:**
```bash
docker build -t java-awesome-starter .
```

**Run container:**
```bash
docker run -p 8080:8080 java-awesome-starter
```

## Configuration Profiles

### Development (`dev`)
- Uses H2 in-memory database
- H2 console enabled at `/h2-console`
- SQL logging enabled
- Hot reload with DevTools

### Test (`test`)
- Uses H2 in-memory database
- Minimal logging
- Optimized for fast test execution

### Production (`prod`)
- Uses PostgreSQL
- Environment-based configuration
- Connection pooling with HikariCP
- Production-optimized settings

Set the active profile:
```bash
export SPRING_PROFILES_ACTIVE=prod
./gradlew bootRun
```

Or via application properties:
```bash
./gradlew bootRun --args='--spring.profiles.active=prod'
```

## Testing

### Run all tests
```bash
./gradlew test
```

### Run architecture tests
```bash
./gradlew test --tests "*architecture.*"
```

### Generate coverage report
```bash
./gradlew jacocoTestReport
```

### Verify coverage requirements (80% minimum)
```bash
./gradlew jacocoTestCoverageVerification
```

Coverage reports are generated in `build/reports/jacoco/test/html/index.html`

## API Documentation

Once the application is running, access:

- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **OpenAPI JSON**: http://localhost:8080/api-docs

### Example Endpoints

**Users API:**
- `GET /api/users` - Get all users
- `GET /api/users/{id}` - Get user by ID
- `POST /api/users` - Create user
- `PUT /api/users/{id}` - Update user
- `DELETE /api/users/{id}` - Delete user

**Products API:**
- `GET /api/products` - Get all products
- `GET /api/products/{id}` - Get product by ID
- `GET /api/products?name={search}` - Search products
- `POST /api/products` - Create product
- `PUT /api/products/{id}` - Update product
- `DELETE /api/products/{id}` - Delete product

## Architecture Principles

This template enforces domain-driven design through ArchUnit tests:

1. **Layer Separation**: Controllers → Services → Repositories
2. **Naming Conventions**:
   - Controllers end with `Controller`
   - Services end with `Service`
   - Repositories end with `Repository`
3. **Package Organization**: Each domain contains `controller`, `service`, `repository`, and `model` packages
4. **Dependency Rules**: Services cannot depend on Controllers
5. **Repository Interfaces**: All repositories must be interfaces (Spring Data JPA)

## CI/CD Pipeline

The GitHub Actions pipeline includes:

1. ✅ Gradle wrapper validation
2. 🏗️ Build compilation
3. 🧪 Unit and integration tests
4. 🏛️ Architecture validation (ArchUnit)
5. 📊 Code coverage reporting (JaCoCo)
6. 🐳 Docker build
7. 🔒 Container vulnerability scanning (Trivy)
8. 📦 Artifact publishing

## Security

- **Non-root user** in Docker container
- **Multi-stage builds** for minimal attack surface
- **Automated vulnerability scanning** with Trivy
- **Dependabot** for dependency updates (configure in repository settings)
- **Health checks** in Docker and Kubernetes
- **Actuator endpoints** secured and limited

## Database Migrations

Migrations are managed with Liquibase in `src/main/resources/db/changelog/`.

**Create a new migration:**
1. Create a new XML file in `db/changelog/changes/`
2. Include it in `db.changelog-master.xml`
3. Run the application to apply migrations

## Contributing

Please read [CONTRIBUTING.md](CONTRIBUTING.md) for details on our code of conduct and development process.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Acknowledgments

- Inspired by enterprise-grade best practices
- Built with Spring Boot and domain-driven design principles
- Designed for production readiness from day one
