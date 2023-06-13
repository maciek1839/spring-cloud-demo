package com.showmeyourcode.spring_cloud.client;

import com.github.tomakehurst.wiremock.WireMockServer;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@Slf4j
@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@ActiveProfiles("test")
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class BaseIT {

    protected static WireMockServer wireMockServer;

    @Autowired
    protected TestRestTemplate restTemplate;
    @BeforeAll
    static void setup() {
        log.info("Starting WireMock services...");
        wireMockServer = new WireMockServer(9871);
        wireMockServer.start();
    }

    @AfterAll
    static void tearDown(){
        log.info("Stopping WireMock services...");
        wireMockServer.stop();
    }

    @AfterEach
    protected void afterEach(){
        wireMockServer.resetAll();
    }
}
