# Credit Card Management:
Microservice to provide api for credit card operations.

# Technology Stack
- Spring Boot 2.4.0
- Spring Data JPA
- Maven
- Docker
- Open API 3.0(Swagger)

# Database
- HSQLDB is used as a database.

# Setting up test environment
- Import maven project in Intellij or Eclipse.
- Docker Build and Run.

```docker
docker build -t creditcardapp .
docker run -p 8080:8080 creditcardapp

```
Maven Run
```mvn
mvn clean install
mvn spring-boot:run
```
- Open browser and go to URL "http://localhost:8080/cardManagement/swagger-ui/index.html" and explore API operations.

# Design considerations to be done
- Integration patterns
- Communication protocol
- Error Handling
- Security (Authentication and Authorization)
- Centralized logging & Distributed Tracing
- High availability and Scalability
- Deployment on multi-cloud platforms