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

## Services specification

- `eureka-server`
    - Dashboard: http://localhost:8761
    - Port: 8761
- `microservice1`
    - SpringFox 3.x
    - Specification: Swagger2
    - Reference: http://localhost:8100/microservice1/swagger-ui/
    - Eureka service name: spring-cloud-eureka-service1
    - Port: 8100
    - Context path: /microservice1
- `microservice2`
    - springdoc-openapi-ui using Swagger UI
    - Specification: OpenApi3 (OAS3)
    - Reference: http://localhost:8200/microservice2/swagger-ui.html
    - Eureka service name: spring-cloud-eureka-service2
    - Port: 8200
    - Context path: /microservice2
- `standalone-service`
    - Springfox 2.x
    - Specification: Swagger 2
    - Reference: http://localhost:8300/standalone-microservice/swagger-ui.html
    - Port: 8300
    - Context path: /standalone-microservice
- `client`
    - SpringFox 3.x
    - Specification: OpenApi3 (OAS3)
    - Reference: http://localhost:8000/swagger-ui/
    - Eureka service name: spring-cloud-eureka-client
    - Spring REST Docs: client/src/main/asciidoc
    - Port: 8000
- `admin-dashboard`
    - Dashboard: http://localhost:9000/
    - Port: 9000

## Getting started

1. (Optional) Enable Lombok annotations in your IDE.
2. (Optional) Run all servers using predefined configurations for IntelliJ (.runconfig)
3. Run Eureka server
    - dashboard: localhost:8761
4. Run microservices which will register with Eureka server.
    - microservice1
    - microservice2
    - standalone-service
5. Run a client which will connect to Eureka and call microservices using Eureka.
    - client

![img](./docs/spring-boot-admin.png)

![img](./docs/spring-boot-eureka.png)

### Spring REST Docs vs Springdoc

Reference: https://www.baeldung.com/spring-rest-docs-vs-openapi

**Spring REST Docs** is a framework developed by the Spring community in order to create accurate documentation for RESTful APIs. The output of running the tests is created as AsciiDoc files which can be put together using Asciidoctor to generate an HTML page describing our APIs.

**Springdoc OpenAPI UI** can generate UI using Swagger UI.

## Spring Cloud components

<img src="docs/spring-cloud.png"  width="800" height="600">

Reference: https://dzone.com/articles/microservice-architecture-with-spring-cloud-and-do

## Swagger 2 vs OpenApi3

<img src="docs/swagger2-vs-openapi3.png"  width="800" height="500">

Reference: https://medium.com/@tgtshanika/open-api-3-0-vs-swagger-2-0-94a80f121022

## References

- https://www.baeldung.com/spring-cloud-netflix-eureka
- https://www.baeldung.com/swagger-2-documentation-for-spring-rest-api
- https://springdoc.org/migrating-from-springfox.html
- https://springdoc.org/faq.html
- https://newbedev.com/how-to-write-integration-tests-with-spring-cloud-netflix-and-feign
- https://www.vojtechruzicka.com/documenting-spring-boot-rest-api-swagger-springfox/
