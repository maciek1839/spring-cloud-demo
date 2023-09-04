package com.showmeyourcode.spring_cloud.demo.factory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import com.showmeyourcode.spring_cloud.demo.factory.service.OrdersService;
import com.showmeyourcode.spring_cloud.demo.factory.service.ItemsService;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.restassured.RestAssuredRestDocumentation;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.test.StepVerifier;

@Slf4j
@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@ActiveProfiles("test")
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class BaseIT {

    @Value("${spring.webflux.base-path}")
    protected String contextPath;
    @Autowired
    protected ObjectMapper objectMapper;
    @Autowired
    protected OrdersService ordersService;
    @Autowired
    protected ItemsService itemsService;

    @LocalServerPort
    protected int port;
    // In order to generate documentation (spring-restdocs), tests must run with MockMvc/WebClient/RestAssured.
    // https://docs.spring.io/spring-restdocs/docs/current/reference/htmlsingle/
    protected RequestSpecification requestSpecification;

    protected Faker faker = new Faker();
    protected String exampleClientHeaderValue = "shop-microservice";

    @BeforeEach
    public void beforeEach(RestDocumentationContextProvider restDocumentation) {
        log.info("Setting up the port: {}", port);
        RestAssured.port = port;
        this.requestSpecification = new RequestSpecBuilder()
                .addFilter(RestAssuredRestDocumentation.documentationConfiguration(restDocumentation))
                .build();

        StepVerifier
                .create(itemsService.deleteAll())
                .expectComplete()
                .verify();
        StepVerifier
                .create(ordersService.deleteAll())
                .expectComplete()
                .verify();
    }

    protected String addContextPath(String currentPath){
        return contextPath+currentPath;
    }
}
