# Yu-Gi-Oh! Deck Tracker

Backend REST API for tracking Yu-Gi-Oh! TCG decks, versions, and tournament results.

## Stack
- Java 21
- Spring Boot 4.1.1
- Spring Data JPA / Hibernate
- PostgreSQL
- Maven

## Status
🚧 In active development.

## Running locally
1. Start PostgreSQL (Docker): `docker run --name yugioh-db -e POSTGRES_USER=dovark -e POSTGRES_PASSWORD=devpassword -e POSTGRES_DB=yugioh_tracker -p 5433:5432 -d postgres:16`
2. Run the app: `./mvnw spring-boot:run`
3. API available at `http://localhost:8080/api/cards`
