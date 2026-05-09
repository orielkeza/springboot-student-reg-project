# Student Registration API

A RESTful backend application built with Spring Boot for managing student records. The system handles full CRUD operations — creating, reading, updating, and deleting student entries — with PostgreSQL as the persistence layer and auto-generated IDs for unique student identification.

---

## Motivation

This project was built to develop hands-on experience with the Spring Boot ecosystem: dependency injection, layered architecture, JPA/Hibernate ORM, REST controller design, and backend testing with JUnit 5 and Mockito. It also served as a practical introduction to API documentation, environment configuration, and systematic debugging.

---

## Features

- Create, read, update, and delete student records
- Auto-generated unique student IDs via JPA ID generation
- Input validation with `Optional` return types for null-safe responses
- Full API documentation via Swagger / OpenAPI (accessible at runtime)
- Unit tests for the service layer with mocked dependencies
- Integration tests for the controller layer using `MockMvc`

---

## Tech Stack

| Layer | Technology |
|---|---|
| Framework | Spring Boot (Java) |
| Database | PostgreSQL |
| ORM | JPA / Hibernate |
| Testing | JUnit 5, Mockito |
| API Docs | Swagger / OpenAPI (springdoc) |
| Build Tool | Maven (Maven Wrapper) |
| Dev Environment | WSL (Ubuntu on Windows) |

---

## Project Structure

```
src/
└── main/
    └── java/com/example/studentregistration/
        ├── controller/    # HTTP request handling, route definitions
        ├── service/       # Business logic layer
        ├── model/         # JPA entity definitions
        └── repository/    # Spring Data JPA interfaces
```

The application follows a standard **Controller → Service → Repository** layered architecture, keeping concerns separated and the codebase maintainable.

---

## Setup and Installation

### Prerequisites

- Java 17+
- PostgreSQL running locally
- Maven (or use the included Maven Wrapper)

### Steps

1. **Clone the repository**
   ```bash
   git clone <repo-url>
   cd student-registration
   ```

2. **Configure environment variables**

   Set the following variables at the system level (recommended) or in your IDE's run configuration:
   ```
   DB_USERNAME=your_postgres_username
   DB_PASSWORD=your_postgres_password
   ```

   > **Note:** System-wide environment variables are recommended so the same credentials are reused across projects without reconfiguring per session.

3. **Install dependencies and build**
   ```bash
   ./mvnw clean install
   ```

4. **Initialize the database**

   Create a PostgreSQL database named `student_db` (or update `application.properties` to match your preferred name). Hibernate will auto-create tables on first run via `spring.jpa.hibernate.ddl-auto=update`.

5. **Run the application**
   ```bash
   ./mvnw spring-boot:run
   ```

   The application starts on `http://localhost:8080` by default.

---

## API Endpoints

| Method | Endpoint | Description |
|---|---|---|
| `GET` | `/students` | Retrieve all students |
| `GET` | `/students/{id}` | Retrieve a student by ID |
| `POST` | `/students` | Register a new student |
| `PUT` | `/students/{id}` | Update an existing student record |
| `DELETE` | `/students/{id}/delete` | Delete a student by ID |

### Example Request (Delete via PowerShell)

```powershell
Invoke-RestMethod -Uri "http://localhost:8080/students/9/delete" -Method DELETE
```

### Interactive API Documentation (Swagger UI)

Once the application is running, full endpoint documentation is available at:

```
http://localhost:8080/swagger-ui/index.html
```
<img width="1403" height="912" alt="image" src="https://github.com/user-attachments/assets/a85585d3-0832-42d4-bb85-5e5a2b6977f2" />

---

## Data Format

**Date of Birth:** `YYYY-MM-DD`

**Example request body (POST /students):**
```json
{
  "firstName": "Jane",
  "lastName": "Doe",
  "email": "jane.doe@example.com",
  "dob": "2001-05-15"
}
```

---

## Testing

Run the full test suite:
```bash
./mvnw clean test
```

Build a packaged JAR (runs tests as part of the build):
```bash
./mvnw clean package
```
## Testing Coverage

<img width="1394" height="287" alt="image" src="https://github.com/user-attachments/assets/45f53248-8fb0-4845-b41c-b458b74bb56e" />
> **Note:** The 37% entry (`com.studentreg.studentlist`) is the Spring Boot 
> application entry point (`@SpringBootApplication`). This class is not 
> unit tested by convention — it exists solely to bootstrap the application context.


### Testing Approach

- **Unit tests (Service layer):** Core business logic is tested in isolation using Mockito to mock the repository. ID generation is also mocked to ensure tests don't depend on database state.
- **Integration tests (Controller layer):** HTTP behavior is tested using `MockMvc`, verifying correct status codes, request mappings, and response payloads.

---

## Challenges and Solutions

**`@RequestMapping` overexposure**
Initially used a generic `@RequestMapping` annotation without specifying an HTTP method, which allowed any HTTP verb to match. Replaced with explicit `@GetMapping`, `@PostMapping`, etc. to enforce proper method constraints and reduce attack surface.

**Environment variable configuration**
Explored three configuration approaches (system-wide, VS Code `.env` file, and command-line injection) before settling on system-wide configuration for consistency across multiple projects.

**Test suite failures**
Several issues surfaced during testing:
- JUnit 4 vs JUnit 5 import conflicts caused tests to not run or throw `NullPointerException` on `when(...)` lines — resolved by standardizing on JUnit 5 imports.
- `404` and `405` errors in controller tests traced back to incorrect URL path definitions and mismatched path variables — fixed by aligning `@PathVariable` names with route definitions.
- Service tests returned null because the mock was not calling `save()` before assertions — resolved by including the save call in test setup.

**WSL vs. PowerShell**
Linux terminal (WSL) proved significantly more reliable than PowerShell for running Maven commands and managing the development environment on Windows.

---

## Known Limitations

- No authentication or authorization — all endpoints are publicly accessible
- No built-in search or filtering by student attributes
- Limited exception handling — edge cases outside of `Optional` return types are not fully covered
- Date of birth input is not validated beyond format (`YYYY-MM-DD`)

---

## Lessons Learned

- Layered Spring Boot architecture and the role of each component (Controller, Service, Repository, Model)
- How JPA/Hibernate maps Java objects to relational tables and handles ID generation
- The importance of mocking the right layer in unit tests — testing the service means mocking the repo, not calling it directly
- Proper HTTP method constraints to prevent unintended route exposure
- How to configure and document a REST API with Swagger/OpenAPI for developer usability

---

## Future Improvements

- [ ] Add Spring Security for basic authentication
- [ ] Implement search/filter endpoints (by name, email, enrollment date)
- [ ] Add input validation with `@Valid` and `ConstraintViolation` error responses
- [ ] Containerize with Docker for portable local setup
- [ ] Expand test coverage to include edge cases and error paths
- [ ] Add pagination to `GET /students` for scalability

---

## Author

Ori built this during a software development internship as a hands-on introduction to backend development with the Spring ecosystem.
