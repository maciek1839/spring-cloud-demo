# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.1.0/), and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## 2.0.0 (2023-09-04)

### Added

- System and acceptance tests ([eeafaf09](https://gitlab.com/ShowMeYourCodeYouTube/spring-cloud-demo/-/commit/eeafaf0955996fd0fd765783298abe3d84be656c))
- Business context to all microservices ([54dd77bf](https://gitlab.com/ShowMeYourCodeYouTube/spring-cloud-demo/-/commit/54dd77bfa51cdafcae7f6b20937c36ab1c823a64))
- SonarCloud integration ([f89813ad](https://gitlab.com/ShowMeYourCodeYouTube/spring-cloud-demo/-/commit/f89813ad543811f3e150640da284c315a8daec59))

### Changed

- Migrate to Spring Boot 3.1 and Spring Cloud 2022.0.4 ([6e8be691](https://gitlab.com/ShowMeYourCodeYouTube/spring-cloud-demo/-/commit/6e8be6915270795c32e93b64e3e4bf4733e317a8))
- Migrate to Java 17 and Spring Boot 2.4 (compatible with Spring Cloud 2020) ([ae0fdade](https://gitlab.com/ShowMeYourCodeYouTube/spring-cloud-demo/-/commit/ae0fdade676731cb7f0de2b518f29214297b2131))
- Replace OpenAPI 2.0 by OpenApi3 (OAS3) ([9d0364c1](https://gitlab.com/ShowMeYourCodeYouTube/spring-cloud-demo/-/commit/9d0364c1d63fd8bcb41cf9b372450790f974c0e4))
- API specification for microservices
  - Add versioning and context paths ([d0c09c47](https://gitlab.com/ShowMeYourCodeYouTube/spring-cloud-demo/-/commit/d0c09c47eb8e0248ab0275f52cf78084fed3d560))
  - Rename the 'client' microservice to 'reporting' ([c91004ec](https://gitlab.com/ShowMeYourCodeYouTube/spring-cloud-demo/-/commit/c91004ec0043d26f180e788efedeb3bcbce21501))
- Update documentation (Spring Cloud projects, Service Discovery) ([c166acc1](https://gitlab.com/ShowMeYourCodeYouTube/spring-cloud-demo/-/commit/c166acc1981aa8c12df8abd4a5cd7bd326cf2d89))

### Fixed

- Snippets in Spring REST Docs are correctly displayed ([0e60389c](https://gitlab.com/ShowMeYourCodeYouTube/spring-cloud-demo/-/commit/0e60389c56f38d428148e04149812d62024ea6cb))

### Removed

- Replace RestTemplate with REST Assured in unit/integration tests ([42571901](https://gitlab.com/ShowMeYourCodeYouTube/spring-cloud-demo/-/commit/42571901bc26186bb049228d202af7ee20c53d5b))

## 1.0.0 (2023-06-14)

### Added

- Spring Cloud services (Eureka Server, OpenFeign, Client Side Load Balancer: Ribbon)
- Spring Boot Admin
- Swagger API for all microservices
- Spring REST Docs for the client microservice
- Gitlab CI/CD configuration
