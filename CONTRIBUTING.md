# Contributing to Java Awesome Starter

Thank you for your interest in contributing to Java Awesome Starter! This document provides guidelines for contributing to the project.

## Code of Conduct

Please be respectful and constructive in all interactions.

## How to Contribute

### Reporting Bugs

1. Check if the bug has already been reported in Issues
2. If not, create a new issue with:
   - Clear title and description
   - Steps to reproduce
   - Expected vs actual behavior
   - Your environment details (Java version, OS, etc.)

### Suggesting Features

1. Check if the feature has already been suggested
2. Create a new issue describing:
   - The problem you're trying to solve
   - Your proposed solution
   - Any alternatives you've considered

### Pull Requests

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Make your changes following the coding standards below
4. Ensure all tests pass (`./gradlew test`)
5. Ensure code coverage meets requirements (`./gradlew jacocoTestCoverageVerification`)
6. Commit your changes with clear messages
7. Push to your fork
8. Open a Pull Request

## Coding Standards

### Java Code Style

- Follow standard Java conventions
- Use meaningful variable and method names
- Keep methods focused and concise
- Add JavaDoc comments for public APIs
- Use Lombok annotations to reduce boilerplate

### Architecture Guidelines

- Follow domain-driven design principles
- Maintain proper layer separation (Controller → Service → Repository)
- Keep domain logic in the service layer
- Use DTOs for API contracts when needed
- Write tests for all business logic

### Testing

- Write unit tests for all service methods
- Maintain minimum 80% code coverage
- Use meaningful test names following `methodName_should_expectedBehavior_when_condition` pattern
- Use ArchUnit tests to enforce architectural rules

### Commit Messages

Follow the conventional commits format:

```
type(scope): subject

body (optional)

footer (optional)
```

Types:
- `feat`: New feature
- `fix`: Bug fix
- `docs`: Documentation changes
- `style`: Code style changes (formatting, etc.)
- `refactor`: Code refactoring
- `test`: Adding or updating tests
- `chore`: Maintenance tasks

Example:
```
feat(user): add email validation endpoint

Implement REST endpoint to validate user email addresses
before registration to improve user experience.

Closes #123
```

## Development Setup

1. Ensure you have JDK 21 installed
2. Clone the repository
3. Run `./gradlew build` to verify everything works
4. Make your changes
5. Run tests with `./gradlew test`

## Questions?

Feel free to open an issue for any questions or concerns.
