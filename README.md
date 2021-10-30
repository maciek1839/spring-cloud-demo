# Spring Cloud demo

**Technical remarks** - [link](./TECHNICAL_REMARKS.md)

## Technology
- JDK 11 (AWS Coretto)
- Spring Boot Cloud
    - OpenFeign
    - Ribbon
    - Eureka
- Spring REST Docs
- Swagger/ ReDoc
- Testcontainers

## Services specification
- `eureka-server`
    - Dashboard: http://localhost:8761
- `standalone-service`
    - Springfox 2.x
    - Specification: Swagger 2
    - Reference: http://localhost:8100/swagger-ui.html
    - Eureka service name: spring-cloud-eureka-client
- `microservice1`
    - SpringFox 3.x
    - Specification: Swagger2
    - Reference: http://localhost:8001/swagger-ui/
  - Eureka service name: spring-cloud-eureka-service1
- `microservice2`
    - springdoc-openapi-ui using Swagger UI
    - Specification: OpenApi3 (OAS3)
    - Reference: http://localhost:8002/swagger-ui.html
  - Eureka service name: spring-cloud-eureka-service2
- `microservice3`
    - springdoc-openapi-ui & ReDoc (as UI)
    - Specification: OpenApi3 (OAS3)
    - Reference
        - ReDoc: http://localhost:8003/documentation.html
        - API specification: http://localhost:8003/v3/api-docs 
    - Eureka service name: spring-cloud-eureka-service3
- `client`
    - SpringFox 3.x
    - Specification: OpenApi3 (OAS3)
    - Reference: http://localhost:8000/swagger-ui/
    - Eureka service name: spring-cloud-eureka-client
    - Spring REST Docs: client/src/main/asciidoc 

## Getting started

1. Run Eureka server
    - dashboard: localhost:8761
1. Run microservices which will register with Eureka server.
    - microservice1
    - microservice2
    - microservice3
    - standalone-service
1. Run a client which will connect to Eureka and call microservices using Eureka.
    - client

## Spring Cloud components
![Spring Cloud](./docs/spring-cloud.png)  
Reference: https://dzone.com/articles/microservice-architecture-with-spring-cloud-and-do

## Swagger 2 vs OpenApi3

![Swagger2 vs OpenApi3](./docs/swagger2-vs-openapi3.png)  
Reference: https://medium.com/@tgtshanika/open-api-3-0-vs-swagger-2-0-94a80f121022

## References
- https://www.baeldung.com/spring-cloud-netflix-eureka
- https://www.baeldung.com/swagger-2-documentation-for-spring-rest-api
- https://springdoc.org/migrating-from-springfox.html
- https://springdoc.org/faq.html
- https://newbedev.com/how-to-write-integration-tests-with-spring-cloud-netflix-and-feign
