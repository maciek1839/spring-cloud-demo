package com.showmeyourcode.spring_cloud.demo.reporting;

import com.github.tomakehurst.wiremock.WireMockServer;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.github.tomakehurst.wiremock.client.WireMock.configureFor;

@Slf4j
@ExtendWith({SpringExtension.class})
@ActiveProfiles("test")
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class BaseIT {

    protected static WireMockServer wireMockServer;
    protected RequestSpecification requestSpecification;
    @LocalServerPort
    protected int port;
    @Value("${server.servlet.context-path}")
    protected String contextPath;

    @BeforeAll
    static void setup() {
        log.info("Starting WireMock services...");
        wireMockServer = new WireMockServer(9871);
        wireMockServer.start();
        configureFor(wireMockServer.port());
    }

    @AfterAll
    static void tearDown(){
        log.info("Stopping WireMock services...");
        wireMockServer.stop();
    }

    @BeforeEach
    public void beforeEach() {
        log.info("Setting up the port: {}", port);
        RestAssured.port = port;
        this.requestSpecification = new RequestSpecBuilder()
                .build();
    }

    @AfterEach
    protected void afterEach(){
        wireMockServer.resetAll();
    }

    protected String addContextPath(String currentPath){
        return contextPath+currentPath;
    }
}
