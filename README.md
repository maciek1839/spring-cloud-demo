# Spring Cloud demo

| Branch |                                                                                         Pipeline                                                                                         |                                                                                      Code coverage                                                                                       |                                       Test report                                        |                                         Spring REST Docs                                         |                                 SonarCloud                                 |
|:------:|:----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------:|:----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------:|:----------------------------------------------------------------------------------------:|:------------------------------------------------------------------------------------------------:|:--------------------------------------------------------------------------:|
| master | [![pipeline status](https://gitlab.com/ShowMeYourCodeYouTube/spring-cloud-demo/badges/master/pipeline.svg)](https://gitlab.com/ShowMeYourCodeYouTube/spring-cloud-demo/-/commits/master) | [![coverage report](https://gitlab.com/ShowMeYourCodeYouTube/spring-cloud-demo/badges/master/coverage.svg)](https://gitlab.com/ShowMeYourCodeYouTube/spring-cloud-demo/-/commits/master) | [link](https://showmeyourcodeyoutube.gitlab.io/spring-cloud-demo/test-report/index.html) | [link](https://showmeyourcodeyoutube.gitlab.io/spring-cloud-demo/rest-docs/client-api-docs.html) | [link](https://sonarcloud.io/organizations/showmeyourcodeyoutube/projects) |


## Technology

- JDK (AWS Corretto)
- Spring Boot Cloud
    - OpenFeign
    - Ribbon
    - Eureka
- Spring REST Docs
- Swagger
- WireMock
- RestAssured & Hamcrest

## Services specification

![img](docs/high-level-design-diagram.drawio.png)

The diagram was created using [https://app.diagrams.net/](https://app.diagrams.net/).

All services use OpenApi3 (OAS3).

- `eureka-server`
  - Technology: Spring MVC
  - Dashboard: http://localhost:8761
  - Port: 8761
- `shop-microservice`
  - Technology: Spring MVC
  - Swagger UI: http://localhost:8100/shop/swagger-ui.html
  - Eureka service name: spring-cloud-eureka-shop
  - Port: 8100
  - Context path: /shop
  - OpenFeign using provided/static URLs or hosts from Ribbon/Eureka (warehouse)
- `warehouse-microservice`
  - Technology: Spring MVC
  - Swagger UI: http://localhost:8200/warehouse/swagger-ui.html
  - Eureka service name: spring-cloud-eureka-warehouse
  - Port: 8200
  - Context path: /warehouse
  - Java API Client configuration using a provided/static URL (factory)
- `factory-microservice`
  - Technology: Spring WebFlux
  - Swagger UI: http://localhost:8300/factory/swagger-ui.html
  - Port: 8300
  - Context path: /factory
- `client-microservice`
  - Technology: Spring MVC
  - Swagger UI: http://localhost:8000/client/swagger-ui.html
  - Eureka service name: spring-cloud-eureka-client
  - Spring REST Docs: client-microservice/src/main/asciidoc
  - Port: 8000
  - Context path: /client
  - RestTemplate using Ribbon with Eureka
- `admin-dashboard`
  - Technology: Spring MVC
  - Dashboard: http://localhost:9000/
  - Port: 9000

---

Design remarks:
- In order to simplify this example, models are reused by microservices. In real applications it might not be a good choice.
- The warehouse microservice is a proxy forwarding requests to the factory microservice.
- The shop microservice is a proxy forwarding requests to the warehouse microservice.
- The client microservice calls arbitrary other services.

## Getting started

1. (Optional) Enable Lombok annotations in your IDE
2. (Optional) Run all servers using predefined configurations for IntelliJ (.runconfig)
   1. If your IDE throws errors because some classes were not found, mark `target/generated-sources` as `Generated Sources Root` in your IDE.
3. Run the Eureka server
4. Run microservices
5. Run a client which calls microservices using Eureka

![img](./docs/spring-boot-admin.png)

![img](./docs/spring-boot-eureka.png)

### Spring REST Docs vs Springdoc

Reference: https://www.baeldung.com/spring-rest-docs-vs-openapi

**Spring REST Docs** is a framework developed by the Spring community in order to create accurate documentation for RESTful APIs. The output of running the tests is created as AsciiDoc files which can be put together using Asciidoctor to generate an HTML page describing our APIs.

**Springdoc OpenAPI UI** can generate UI using Swagger UI.

## Spring Cloud components

![img](docs/spring-cloud.png)

Reference: https://dzone.com/articles/microservice-architecture-with-spring-cloud-and-do

## Swagger 2 vs OpenApi3

![img](docs/swagger2-vs-openapi3.png)

**OpenAPI 3 is the successor of the widely used OpenAPI/Swagger 2.0 format, for machine-readable API definitions.**

- Reference: https://dev.to/frolovdev/openapi-spec-swagger-v2-vs-v3-4o7c
- Official documentation: https://spec.openapis.org/oas/v3.1.0

## Spring REST Docs

Spring REST Docs helps you to document RESTful services.

It combines hand-written documentation written with Asciidoctor and auto-generated snippets produced with Spring MVC Test. This approach frees you from the limitations of the documentation produced by tools like Swagger.

It helps you to produce documentation that is accurate, concise, and well-structured. This documentation then allows your users to get the information they need with a minimum of fuss.

Ref: https://spring.io/projects/spring-restdocs#overview

![img](docs/springs-docs-generated-api-doc.png)

Example implementation - [spring-projects / spring-restdocs](https://github.com/spring-projects/spring-restdocs/blob/2.0.x/samples/rest-notes-spring-data-rest/src/main/asciidoc/api-guide.adoc)
