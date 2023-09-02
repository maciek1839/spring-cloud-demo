package com.showmeyourcode.spring_cloud.demo.shop.configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {

    private static final String API_VERSION = "0.0.1";

    @Bean
    public OpenAPI microserviceOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Shop Microservice API")
                        .description("Spring Cloud Application - Shop Microservice. This microservice is a proxy forwarding requests to the warehouse microservice.")
                        .version(API_VERSION)
                        .license(new License().name("MIT").url("https://opensource.org/license/mit/")))
                .externalDocs(new ExternalDocumentation()
                        .description("ShowMeYourCodeYouTube - GitLab")
                        .url("https://gitlab.com/ShowMeYourCodeYouTube"));
    }
}
