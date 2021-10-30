# Spring Cloud demo

## Technology
- JDK 11 (AWS Coretto)
- Spring Boot Cloud
- Spring REST Docs
- OpenFeign
- Swagger/ ReDoc

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

## Getting started

1. Run Eureka server
    - dashboard: localhost:8761
1. Run microservices which will register with Eureka server.
    - microservice1
    - microservice2
        - Swagger: http://localhost:8002/swagger-ui/
    - standalone-service
        - Swagger: http://localhost:8100/swagger-ui.html
1. Run a client which will connect to Eureka and call microservices using Eureka.
    - client

## Components
- **Eureka**
   - An application that holds the information about all client-service applications.
- *Feign/OpenFeign*
   - A declarative web service client.
   - https://cloud.spring.io/spring-cloud-netflix/multi/multi_spring-cloud-feign.html
- Ribbon load balancer

## Spring REST Docs vs Springdoc
Reference: https://www.baeldung.com/spring-rest-docs-vs-openapi

**Spring REST Docs** is a framework developed by the Spring community in order to create accurate documentation for RESTful APIs. The output of running the tests is created as AsciiDoc files which can be put together using Asciidoctor to generate an HTML page describing our APIs.

**Springdoc OpenAPI UI** can generate UI using Swagger UI.

## Swagger 2 vs OpenApi3

![Swagger2 vs OpenApi3](./docs/swagger2-vs-openapi3.png)  
Reference: https://medium.com/@tgtshanika/open-api-3-0-vs-swagger-2-0-94a80f121022

## References
- https://www.baeldung.com/spring-cloud-netflix-eureka
- https://www.baeldung.com/swagger-2-documentation-for-spring-rest-api
- https://springdoc.org/migrating-from-springfox.html
- https://springdoc.org/faq.html
