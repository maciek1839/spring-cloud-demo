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
  - Spring REST Docs: factory-microservice/src/main/asciidoc
  - Port: 8300
  - Context path: /factory
- `client-microservice`
  - Technology: Spring MVC
  - Swagger UI: http://localhost:8000/client/swagger-ui.html
  - Eureka service name: spring-cloud-eureka-client
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

## Testing Strategy

> Acceptance Testing is done after the system testing. It is used to check whether the software meets the customer requirements or not. Acceptance testing is used by testers, stakeholders as well as clients.
>
> Reference: https://www.geeksforgeeks.org/difference-between-system-testing-and-acceptance-testing/

![img](docs/testing-strategy.jpg)

[Reference](https://www.geeksforgeeks.org/acceptance-testing-software-testing/)

System and acceptance tests are in the `test-util` module.
Run them when all microservices are up.

Thanks to automatic tests you don't need to manually verify is services are fine after making major changes.

Other good articles about testing levels:
- https://www.guru99.com/levels-of-testing.html

## Primary Projects of Spring Cloud

![img](docs/microservices-6.svg)

[Reference](https://spring.io/microservices)


- Spring Cloud Config
  - Centralized external configuration management backed by a git repository. The configuration resources map directly to Spring Environment but could be used by non-Spring applications if desired.
- Spring Cloud Netflix
  - Integration with various Netflix OSS components (Eureka, Hystrix, Zuul, Archaius, etc.):
    - Service Discovery (Eureka)
    - Circuit Breaker (Hystrix)
    - Intelligent Routing (Zuul)
    - Client Side Load Balancing (Ribbon)
- Spring Cloud Bus
  - An event bus for linking services and service instances together with distributed messaging. Useful for propagating state changes across a cluster (e.g. config change events).
- Spring Cloud Open Service Broker
  - Provides a starting point for building a service broker that implements the Open Service Broker API.
- Spring Cloud Consul
  - Service discovery and configuration management with Hashicorp Consul.
- Spring Cloud Sleuth
  - Distributed tracing for Spring Cloud applications, compatible with Zipkin, HTrace and log-based (e.g. ELK) tracing.
- Spring Cloud Data Flow
  - A cloud-native orchestration service for composable microservice applications on modern runtimes. Easy-to-use DSL, drag-and-drop GUI, and REST-APIs together simplifies the overall orchestration of microservice based data pipelines.
- Spring Cloud Stream
  - A lightweight event-driven microservices framework to quickly build applications that can connect to external systems. Simple declarative model to send and receive messages using Apache Kafka or RabbitMQ between Spring Boot apps.
- Spring Cloud Stream Applications
  - Spring Cloud Stream Applications are out of the box Spring Boot applications providing integration with external middleware systems such as Apache Kafka, RabbitMQ etc. using the binder abstraction in Spring Cloud Stream.
- Spring Cloud Task
  - A short-lived microservices framework to quickly build applications that perform finite amounts of data processing.
  - Simple declarative for adding both functional and non-functional features to Spring Boot apps.
- Spring Cloud Task App Starters
  - Spring Cloud Task App Starters are Spring Boot applications that may be any process including Spring Batch jobs that do not run forever, and they end/stop after a finite period of data processing.
- Spring Cloud Zookeeper
  - Service discovery and configuration management with Apache Zookeeper.
- Spring Cloud Contract
  - Spring Cloud Contract is an umbrella project holding solutions that help users in successfully implementing the Consumer Driven Contracts approach.
- Spring Cloud Gateway
  - Spring Cloud Gateway is an intelligent and programmable router based on Project Reactor.
- Spring Cloud OpenFeign
  - Spring Cloud OpenFeign provides integrations for Spring Boot apps through autoconfiguration and binding to the Spring Environment and other Spring programming model idioms.
- Spring Cloud Function
  - Spring Cloud Function promotes the implementation of business logic via functions.

---

- Spring Cloud documentations
  - https://spring.io/projects/spring-cloud
- Spring Cloud releases
  - https://github.com/spring-cloud/spring-cloud-release/wiki
- Spring Boot & Spring Cloud supported versions
  - https://github.com/spring-cloud/spring-cloud-release/wiki/Supported-Versions

### Spring Cloud Netflix components replacements

| CURRENT	                    | REPLACEMENT                                       |
|-----------------------------|---------------------------------------------------|
| Hystrix	                    | Resilience4j                                      |
| Hystrix Dashboard / Turbine | 	Micrometer + Monitoring System                   |
| Ribbon                      | 	Spring Cloud Loadbalancer                        |
| Zuul 1	                     | Spring Cloud Gateway                              |
| Archaius 1	                 | Spring Boot external config + Spring Cloud Config |

## Service Discovery in Microservices

A microservice needs to know the location (IP address and port) of every service it communicates with.
If we don’t employ a Service Discovery mechanism, service locations become coupled,
leading to a system that’s difficult to maintain.
We could wire the locations or inject them via configuration in a traditional application,
but it isn’t recommended in a modern cloud-based application of this kind.

The Service Discovery mechanism helps us know where each instance is located.
In this way, a Service Discovery component acts as a registry in which the addresses of all instances are tracked.

### How Does Service Discovery Works?

![img](docs/Service-Discovery-1-1.png)

Let’s describe the steps illustrated in the diagram:
1. The location of the Service Provider is sent to the Service Registry (a database containing the locations of all available service instances).
2. The Service Consumer asks the Service Discovery Server for the location of the Service Provider.
3. The location of the Service Provider is searched by the Service Registry in its internal database and returned to the Service Consumer.
4. The Service Consumer can now make direct requests to the Service Provider.

There are two main Service Discovery patterns: Client‑Side Discovery and Server‑Side Discovery.

### Client-Side Service Discovery

![img](docs/netflix-eureka-service-discovery.png)

[Reference](https://www.codeprimers.com/client-side-service-discovery-in-spring-boot-with-netflix-eureka/)

When using Client-Side Discovery, the Service Consumer is responsible for determining the network locations of available service instances and load balancing requests between them.
The client queries the Service Register.
Then the client uses a load-balancing algorithm to choose one of the available service instances and performs a request.

![img](docs/Service-Discovery-Client-Side.png)

Giving responsibility for client-side load balancing is both a burden and an advantage.
It’s an advantage because it saves an extra hop that we would’ve had with a dedicated load balancer.
It’s a disadvantage because the Service Consumer must implement the load balancing logic.

We can also point out that the Service Consumer and the Service Registry are quite coupled.
This means that Client-Side Discovery logic must be implemented for each programming language and framework used by the Service Consumers.

### Server-Side Service Discovery

![img](docs/Service-Discovery-Server-Side.png)

The alternate approach to Service Discovery is the Server-Side Discovery model,
which uses an intermediary that acts as a Load Balancer.
The client makes a request to a service via a load balancer that acts as an orchestrator.
The load balancer queries the Service Registry and routes each request to an available service instance.

In this approach, a dedicated actor, the Load Balancer, does the job of load balancing.
This is the main advantage of this approach.
Indeed, creating this level of abstraction makes the Service Consumer lighter, as it doesn’t have to deal with the lookup procedure.
As a matter of fact, there’s no need to implement the discovery logic separately for each language and framework that the Service Consumer uses.

On the other hand, we must set up and manage the Load Balancer, unless it’s already provided in the deployment environment.

### What Is Service Registry?

The Service Register is a crucial part of service identification.
It’s a database containing the network locations of service instances.
A Service Registry must be highly available and up-to-date.
Clients can cache the network paths obtained from the Service Registry; however, this information eventually becomes obsolete, and clients won’t reach the service instances.
Consequently, a Service Registry consists of a cluster of servers that use a replication protocol to maintain consistency.

Service Registration options:
- Self-Registration
- Third-party Registration

### References

- https://www.baeldung.com/cs/service-discovery-microservices

## Spring REST Docs vs Springdoc

Reference: https://www.baeldung.com/spring-rest-docs-vs-openapi

**Spring REST Docs** is a framework developed by the Spring community in order to create accurate documentation for RESTful APIs. The output of running the tests is created as AsciiDoc files which can be put together using Asciidoctor to generate an HTML page describing our APIs.

**Springdoc OpenAPI UI** can generate UI using Swagger UI.

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
