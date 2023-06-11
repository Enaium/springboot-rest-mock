# springboot-rest-mock

## Description

This project is a simple REST API and a mock test for it.

## Requirements

- Java
- Gradle
- Spring Boot
- Jimmer
- Lombok
- SpringDoc
- H2 Database

## Database relationship

- One to Many (Member -> Post)
- One to Many (Member -> Comment)
- One to Many (Post -> Comment)
- Many to One (Post -> Member)
- Many to One (Comment -> Member)
- Many to One (Comment -> Post)