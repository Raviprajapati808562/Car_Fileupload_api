# Calculator Spring Boot Assignment — MySQL + Flyway

## Requirements covered

- Java 17
- Spring Boot 3.5.6
- Spring Boot REST APIs
- BODMAS/operator precedence
- Parentheses
- Multiple operands
- OOP design
- Strategy-style operation abstraction
- Easy extension for additional operations
- Calculation history
- JPA + MySQL
- Flyway database migration
- DTOs instead of exposing entities directly
- Global exception handling
- Validation
- Unit tests

## Database setup

Create the MySQL database before starting the application:

```sql
CREATE DATABASE calculator_db;
```

Update `src/main/resources/application.properties` if your MySQL username/password are different:

```properties
spring.datasource.username=root
spring.datasource.password=root
```

## Important JPA/Flyway configuration

The project intentionally uses:

```properties
spring.jpa.hibernate.ddl-auto=validate
spring.flyway.enabled=true
```

Hibernate validates that the Java entity matches the database schema. Flyway is responsible for creating and versioning the schema.

Migration:

```text
src/main/resources/db/migration/V1__create_calculation_history.sql
```

Flyway creates its own `flyway_schema_history` table to track applied migrations.

## Architecture

Client
  -> CalculatorController
  -> CalculatorService
  -> ExpressionParser
  -> Operation implementations
  -> Result
  -> History persistence
  -> CalculationHistoryRepository
  -> MySQL

Database schema is managed by Flyway.

## API

### Calculate

POST `/api/calculator/calculate`

Request:

```json
{
  "expression": "1-2+3-5*4+5*(6+7)"
}
```

Response:

```json
{
  "expression": "1-2+3-5*4+5*(6+7)",
  "result": 47,
  "calculatedAt": "2026-10-02T22:00:00"
}
```

The expression evaluates to 47.

### History

GET `/api/calculator/history`

Returns previous calculations.

## Run

```bash
mvn clean test
mvn spring-boot:run
```

Or:

```bash
mvn clean package
java -jar target/calculator-0.0.1-SNAPSHOT.jar
```

## OOP design

`Operation` is a custom interface. Addition, subtraction, multiplication and division are separate implementations.

The parser is responsible for BODMAS and the service coordinates the use cases.

To add another binary operation, create another `Operation` implementation and register its symbol in the operation factory.

For a future scientific calculator, unary operations such as square root, sine and cosine can be introduced through a separate `UnaryOperation` abstraction without changing the REST API contract.

## Design decisions

1. `BigDecimal` is used for calculator results to avoid common floating-point precision problems.
2. Parser logic is separated from the service so BODMAS does not make the service class complex.
3. DTOs are used so database entities are not exposed directly from REST APIs.
4. History is persisted after successful calculation.
5. Flyway owns database schema changes; Hibernate only validates the schema.
6. Global exception handling gives consistent error responses.

## Rejected alternatives

- MySQL was not used because the target environment uses MySQL.
- `ddl-auto=update` was avoided because Flyway should be the source of truth for schema changes.
- Java `ScriptEngine` was not used because arbitrary expression evaluation is not a good production design and Nashorn is no longer part of modern JDKs.
- A large `if/else` or `switch` for every operation was avoided because it makes extension harder.
