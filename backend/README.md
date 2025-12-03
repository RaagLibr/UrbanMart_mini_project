# Urban Mart Backend

This is the Spring Boot backend for the Urban Mart e-commerce application.

## Tech Stack
- Java 21
- Spring Boot 3.2.3
- Spring Security (JWT)
- Spring Data JPA
- MySQL
- Maven

## Prerequisites
1. **Java 21**: Ensure JDK 21 is installed and `JAVA_HOME` is set.
2. **Maven**: Ensure `mvn` is installed and in your PATH.
3. **MySQL**: Ensure MySQL is running and a database named `urbanmart` exists.

## Configuration
Update `src/main/resources/application.properties` with your database credentials:
```properties
spring.datasource.username=your_username
spring.datasource.password=your_password
```

## Running the Application
To run the application locally:
```bash
mvn spring-boot:run
```
The server will start on `http://localhost:8080`.

## API Endpoints
- **Auth**: `/api/auth/login`, `/api/auth/register`
- **Products**: `/api/products` (GET public, POST/PUT/DELETE Admin only)
- **Orders**: `/api/orders` (POST place order, GET my orders)

## User Roles
- **USER**: Can browse products and place orders.
- **ADMIN**: Can manage products.
