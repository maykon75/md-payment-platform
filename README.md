# md-payment-platform

## Description
Payment platform API developed with Java and Spring Boot using Hexagonal Architecture principles.

The application supports user creation, balance deposit, and money transfer operations between users with validation rules, external authorization, and notification integration.

---

# Technologies

- Java 21
- Spring Boot 4.0.6
- Maven
- Spring Data JPA
- H2 Database
- WebClient
- MapStruct
- JUnit 5
- Mockito
- Swagger (OpenAPI)

---

# Features

- User registration
- CPF/CNPJ validation logic
- User type definition:
    - CPF → COMMON
    - CNPJ → MERCHANT
- Balance deposit
- Money transfer between users
- Merchant transfer restriction
- Insufficient balance validation
- External authorization service integration
- External notification service integration
- Unit tests
- Integration tests
- Global exception handling

---

# Architecture

This project follows Hexagonal Architecture (Ports and Adapters):

```text
src/main/java
├── application
├── domain
├── adapter
│   ├── input
│   └── output
```

## Layers

### Domain
Contains business models.

### Application
Contains use cases, ports, and business validations.

### Adapter Input
REST controllers and request/response DTOs.

### Adapter Output
Persistence, external APIs, and integrations.

---

# Requirements

- Java 21 installed
- Maven 3.9.x or higher installed

---

# Build and Run

## Build the project

```bash
mvn clean install
```

## Run the project

```bash
mvn spring-boot:run
```

---

# API Endpoints

## User

### Create User

```http
POST /user
```

Example Request:

```json
{
  "fullName": "Common User",
  "cpfCnpj": "12345678900",
  "email": "user@email.com",
  "password": "123456"
}
```

---

### Get All Users

```http
GET /user
```

---

# Deposit

### Deposit Balance

```http
PATCH /deposit
```

Example Request:

```json
{
  "id": 1,
  "balance": 100.00
}
```

---

# Transfer

### Transfer Funds

```http
POST /transfer
```

Example Request:

```json
{
  "payer": 1,
  "payee": 2,
  "value": 50.00
}
```
---

# Business Rules

- Merchant users cannot transfer funds
- Transfer amount must be greater than zero
- Deposit amount must be greater than zero
- Transfer cannot exceed payer balance
- CPF/CNPJ and email must be unique
