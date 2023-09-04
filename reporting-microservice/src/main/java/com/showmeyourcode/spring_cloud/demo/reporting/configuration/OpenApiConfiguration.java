package com.showmeyourcode.spring_cloud.demo.reporting.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(
                title = "Reporting Microservice API",
                version = "0.0.1",
                description = "API specification for Reporting Microservice"
        )
)
@Configuration
public class OpenApiConfiguration {
}
