package com.showmeyourcode.spring_cloud.microservice2.configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfiguration {

    private static final String API_VERSION = "0.0.1";

    @Bean
    public OpenAPI microserviceOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Microservice2 API")
                        .description("Spring Cloud Application - Microservice2")
                        .version(API_VERSION)
                        .license(new License().name("MIT").url("https://opensource.org/license/mit/")))
                .externalDocs(new ExternalDocumentation()
                        .description("ShowMeYourCodeYouTube - GitLab")
                        .url("https://gitlab.com/ShowMeYourCodeYouTube"));
    }
}
