# ReadCycle

ReadCycle is a book exchange platform that helps users buy, sell, and exchange books.

The backend is built with Java and Spring Boot and provides REST APIs for user and book management, along with search, pagination, sorting, validation, and exception handling.

## Tech Stack

- Java
- Spring Boot
- Spring Data JPA
- Hibernate
- MySQL
- REST APIs
- Maven
- Postman
- Git & GitHub

## Features

### User Management
- Create a user
- Get all users
- Get a user by ID
- Update user details
- Delete a user

### Book Management
- Create a book listing
- Get all books
- Get a book by ID
- Update book details
- Delete a book

### Book Search
- Search books by title
- Case-insensitive search

### Pagination & Sorting
- Paginated book results
- Sort books by supported fields
- Ascending and descending sorting

### Validation & Error Handling
- Request validation
- Global exception handling
- Structured error responses

## API

The backend exposes RESTful endpoints for users and books.

Example:

```text
GET /books/search?title=java&page=0&size=20&sortBy=title&direction=asc
