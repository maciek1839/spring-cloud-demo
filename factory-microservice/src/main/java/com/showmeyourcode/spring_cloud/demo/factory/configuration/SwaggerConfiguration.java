package com.showmeyourcode.spring_cloud.demo.factory.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(
                title = "Factory Microservice API",
                version = "0.0.1",
                description = "API specification for Factory Microservice"
        )
)
@Configuration
public class SwaggerConfiguration {
}
