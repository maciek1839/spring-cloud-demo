package com.showmeyourcode.spring_cloud.client;

import com.github.tomakehurst.wiremock.WireMockServer;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@Slf4j
@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@ActiveProfiles("test")
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class BaseIT {

    protected static WireMockServer wireMockServer;
    @Autowired
    protected TestRestTemplate restTemplate;
    @Autowired
    private WebApplicationContext webApplicationContext;
    // In order to generate documentation (spring-restdocs), tests must run with MockMvc/WebClient/RestAssured.
    // https://docs.spring.io/spring-restdocs/docs/current/reference/htmlsingle/
    protected MockMvc mockMvc;

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

    @BeforeEach
    protected void beforeEach(WebApplicationContext webApplicationContext,
                              RestDocumentationContextProvider restDocumentation) {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(MockMvcRestDocumentation.documentationConfiguration(restDocumentation))
                .build();
    }

    @AfterEach
    protected void afterEach(){
        wireMockServer.resetAll();
    }
}
