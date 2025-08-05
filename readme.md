# Store manager API

Spring Boot api for managing a store's products.

Features:
-
- CRUD operations on products
- Role based auth
- OpenAPI documentation and Swagger interface for ease of use
- Kafka integration for product related events

Prerequisites
-
- Java 17+
- Maven
- Docker

Get the sources
-
```bash

git clone https://github.com/AndreiMihalache/storeManager.git
cd storeManager
```

Build the containers
-
```bash

docker compose up -d
```
Run the app
-
```bash

mvn spring-boot:run
```

Swagger Endpoints
-
- Swagger UI: http://localhost:8080/swagger-ui.html
- Open API docs: http://localhost:8080/v3/api-docs

Initial users
-
For POST/PUT/DELETE:\
Username: admin \
Password: admin


Default user:\
Username: user \
Password: user

Postman Collection
-
Collection available to import, under project files: \
StoreManagerPostmanCollection.json

