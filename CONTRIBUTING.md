# CONTRIBUTING

- Follow [Conventional Commits](https://www.conventionalcommits.org/en/v1.0.0/).
    - References:
      - https://gist.github.com/joshbuchea/6f47e86d2510bce28f8e7f42ae84c716
      - https://ec.europa.eu/component-library/v1.15.0/eu/docs/conventions/git/
- Use assertions from `Hamcrest` instead of `Junit` in order to be consistent with RestAssured across the project.
  - Hamcrest is required by RestAssured.

## Maven commands

- mvn clean install -DskipTests
- mvn dependency:tree -Ddetail=true

### Release a new version

```text
mvn release:prepare
```

If you want to only update versions (not recommended), use below command:

```text
mvn release:update-versions -DautoVersionSubmodules=true
```

## Integration tests

When you run tests for microservices,
they will automatically update API schemas in corresponding modules.
It's good when you are making changes to API.
Thanks to it, it's easy to keep the latest schema.
