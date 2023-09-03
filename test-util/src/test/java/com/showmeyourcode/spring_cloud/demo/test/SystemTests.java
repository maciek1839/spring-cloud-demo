package com.showmeyourcode.spring_cloud.demo.test;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

@Slf4j
@RequiredArgsConstructor
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SystemTests {

    private static final InfrastructureVerifier infrastructureVerifier = new InfrastructureVerifier();

    private final WebTestClient clientMicroserviceWebClient = TestHelper.clientMicroserviceWebClient();
    private final WebTestClient shopMicroserviceWebClient = TestHelper.shopMicroserviceWebClient();

    @BeforeAll
    static void setup() {
        log.info("Starting system tests...");
        infrastructureVerifier.verifyAllServicesArUp();
        infrastructureVerifier.verifyAllServicesAreRegisteredInEureka();
    }

    @AfterAll
    static void tearDown(){
        log.info("System tests execution finished.");
    }

    @Order(1)
    @Test
    void shouldClientMicroserviceCommunicateToWarehouse(){
        clientMicroserviceWebClient
                .get()
                .uri(InfrastructureConstant.V1_CLIENT_WAREHOUSE_PROXY)
                .exchange()
                .expectStatus()
                .isOk()
                .expectHeader()
                .contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.build.name").isEqualTo("warehouse-microservice")
                .jsonPath("$.build.artifact").isEqualTo("warehouse-microservice");
    }

    @Order(2)
    @Test
    void shouldShopMicroserviceCommunicateToOtherMicroservices(){
        shopMicroserviceWebClient
                .get()
                .uri(InfrastructureConstant.V1_ITEMS)
                .exchange()
                .expectStatus()
                .isOk()
                .expectHeader()
                .contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$").isArray();
    }
}
