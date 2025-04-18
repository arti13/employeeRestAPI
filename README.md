# 🧑‍💼 Employee Management API (Spring Boot + H2 + AOP)

This Spring Boot application provides RESTful APIs for managing employee data stored in an **in-memory H2 database**. It uses **Spring Data JPA** for database interaction and includes a custom **AOP-based execution time logger** using `@LogExecutionTime`.

---

## 🚀 Features

- 🔍 Get employee details by ID (`/employees/{id}`)
- 💾 In-memory H2 database (no installation required)
- 🧮 Auto-incrementing employee IDs
- ⏱️ Logs execution time of methods annotated with `@LogExecutionTime`
- 📅 Retrieve employees who joined in the last 30 days
- 📐 Use of **Spring Data Projection** with custom `@Query` for optimized and partial data fetching

---

## 📦 Technologies Used

- Java 8+
- Spring Boot 3.x
- Spring Data JPA
- Spring AOP
- H2 Database
- Maven

---

## 📂 Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── com/
│   │       └── employee/
│   │           ├── aspect/         # AOP aspects (e.g., LogExecutionTimeAspect)
│   │           ├── controller/     # REST controllers
│   │           ├── dto/            # Data Transfer Objects
│   │           ├── logging/        # Logging utilities or configs
│   │           ├── projection/     # Interface-based projections (if any)
│   │           ├── repository/     # Spring Data JPA repositories
│   │           └── service/        # Service layer
│   └── resources/
│       ├── application.properties  # App configuration
│       └── ...
```
