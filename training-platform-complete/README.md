# Training Platform — Take-Home Backend Exercise

This project implements the supplied Java backend take-home exercise.

## Stack

- Java 17
- Spring Boot 3.5.6
- Maven multi-module build
- Spring Data JPA
- H2 relational database
- Flyway migrations
- Spring application events for in-process notifications
- ArchUnit architecture test
- Spring Boot integration test

## Modules

```text
training-platform
├── catalog-api
├── catalog-core
├── enrollments-api
├── enrollments-core
├── notifications-api
├── notifications-core
└── app
```

### Dependency rules

```text
catalog-core       -> catalog-api
enrollments-core   -> enrollments-api
enrollments-core   -> catalog-api
notifications-core -> notifications-api
notifications-core -> enrollments-api

app -> catalog-core
app -> enrollments-core
app -> notifications-core
```

No core module depends on another core module.

The API modules contain contracts, records, and domain events. They do not contain Spring/JPA implementation.

## Main flows

### Create course

```http
POST /api/v1/courses
Content-Type: application/json

{
  "title": "Java Microservices",
  "capacity": 2,
  "city": "Gurugram"
}
```

### Publish course

```http
POST /api/v1/courses/1/publish
```

Publishing emits `CoursePublishedEvent`. The notification-side handler observes it using the in-process Spring event mechanism.

### Get course

```http
GET /api/v1/courses/1
```

### Enroll

```http
POST /api/v1/enrollments
Content-Type: application/json

{
  "name": "Ravi",
  "email": "ravi@example.com",
  "courseId": 1
}
```

Enrollment checks the course through the `CoursesApi` contract. It does not access catalog entities, repositories, or tables.

The course must exist, be published, and have available capacity.

### List enrollments

```http
GET /api/v1/enrollments?courseId=1
```

## Run

From the repository root:

```bash
mvn clean test
```

Run the application:

```bash
mvn spring-boot:run -pl app -am
```

The application starts on:

```text
http://localhost:8080
```

There is no UI. Use Postman, curl, or the integration tests.

## Events

The exercise asks for an abstraction that can later be replaced by a broker. The implementation here is deliberately in-process.

Two published domain events are used:

- `CoursePublishedEvent` in `catalog-api`
- `EnrollmentCreatedEvent` in `enrollments-api`

The events are published with Spring's `ApplicationEventPublisher` and consumed with `@EventListener`.

The notification implementation logs the confirmation instead of sending a real email.

## Notifications extensibility

`NotificationSender` is the notification contract.

Current implementation:

```text
NotificationSender
       |
       +-- LogNotificationSender
```

A second channel can be added by creating another implementation, for example:

```text
SmsNotificationSender implements NotificationSender
```

without changing `EnrollmentService`.

## Why API/Core?

`-api` is the contract visible to other modules.

`-core` contains the implementation and infrastructure.

For the cross-domain synchronous call:

```text
enrollments-core
       |
       v
   CoursesApi
       ^
       |
catalog-core / CourseService
```

The caller therefore depends on the stable contract rather than catalog implementation details.

This also leaves a seam where the implementation could later become an HTTP client without changing the enrollment business logic.

## Architecture test

`ArchitectureTest` prevents a core module from depending on another domain's core module.

This makes the module boundary a build-time rule rather than a convention.

## Integration test

`TrainingPlatformIntegrationTest` covers:

```text
create course
    ↓
publish course
    ↓
enroll visitor
    ↓
list enrollment
```

During enrollment creation, `EnrollmentCreatedEvent` is published and the notification handler logs a confirmation.

## Decisions / alternatives

### 1. Maven multi-module instead of one module

Rejected a single-module package-only structure because the exercise explicitly evaluates real module boundaries and requires the dependency rules at build level.

### 2. H2 instead of Testcontainers/Postgres

Rejected Testcontainers for this timeboxed exercise. H2 provides a relational database with very little setup and is sufficient for the requested flows.

### 3. Spring application events instead of Kafka

Rejected Kafka because the exercise explicitly asks for an in-process notification mechanism and says a message broker is not required.

### 4. DTO records at boundaries

Rejected exposing JPA entities across modules. DTO records keep persistence implementation details inside the owning core.

### 5. API interface for catalog lookup

Rejected injecting `CourseService` directly into enrollment. Enrollment depends on `CoursesApi`, leaving the implementation replaceable.

## Deliberately skipped

- Authentication/authorization
- UI
- Docker/Kubernetes
- Message broker infrastructure
- Production observability
- Exhaustive validation
- Pretty/custom error JSON
- Real email/SMS delivery
- Advanced concurrency/locking for high-volume capacity allocation

These are outside the exercise's stated non-goals/timebox.
