package com.showmeyourcode.spring_cloud.demo.shop;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.netflix.discovery.EurekaClient;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@Slf4j
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class BaseIT {

    protected static WireMockServer wireMockServer;

    @Value("${server.servlet.context-path}")
    protected String contextPath;
    @MockBean
    protected EurekaClient eurekaClient;
    @Autowired
    protected ObjectMapper objectMapper;
    protected RequestSpecification requestSpecification;
    @LocalServerPort
    protected int port;
    protected String exampleClientHeaderValue = "shop-microservice";
    protected Faker faker = new Faker();

    @BeforeAll
    static void setup() {
        log.info("Starting WireMock services...");
        wireMockServer = new WireMockServer(9871);
        wireMockServer.start();
    }

    @AfterAll
    static void tearDown() {
        log.info("Stopping WireMock services...");
        wireMockServer.stop();
    }

    @BeforeEach
    public void beforeEach() {
        log.info("Setting up the port: {}", port);
        RestAssured.port = port;
        this.requestSpecification = new RequestSpecBuilder().build();
        wireMockServer.resetAll();
    }

    protected String addContextPath(String currentPath) {
        return contextPath + currentPath;
    }
}
