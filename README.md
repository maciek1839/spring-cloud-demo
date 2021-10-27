# Spring Cloud demo

## Technology
- JDK 11 (AWS Coretto)
- Spring Boot Cloud
- OpenFeign
- Swagger

## Getting started

1. Run Eureka server
    - dashboard: localhost:8761
1. Run microservices which will register with Eureka server.
    - microservice1
    - microservice2
        - Swagger: http://localhost:8002/swagger-ui/
1. Run a client which will connect to Eureka and call microservices using Eureka.
    - client

## Components
- **Eureka**
   - An application that holds the information about all client-service applications.
- *Feign/OpenFeign*
   - A declarative web service client.
   - https://cloud.spring.io/spring-cloud-netflix/multi/multi_spring-cloud-feign.html
- Ribbon load balancer


## References
- https://www.baeldung.com/spring-cloud-netflix-eureka
- https://www.baeldung.com/swagger-2-documentation-for-spring-rest-api
