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
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

@Slf4j
@RequiredArgsConstructor
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AcceptanceTests {

    private static final InfrastructureVerifier infrastructureVerifier = new InfrastructureVerifier();

    private final WebTestClient clientMicroserviceWebClient = TestHelper.clientMicroserviceWebClient();
    private final WebTestClient shopMicroserviceWebClient = TestHelper.shopMicroserviceWebClient();
    private final WebTestClient factoryMicroserviceWebClient = TestHelper.factoryMicroserviceWebClient();
    private final WebTestClient warehouseMicroserviceWebClient = TestHelper.warehouseMicroserviceWebClient();
    private final WebTestClient eurekaMicroserviceWebClient = TestHelper.eurekaMicroserviceWebClient();
    private final WebTestClient adminMicroserviceWebClient = TestHelper.adminMicroserviceWebClient();

    @BeforeAll
    static void setup() {
        log.info("Starting acceptance tests...");
        infrastructureVerifier.verifyAllServicesArUp();
        infrastructureVerifier.verifyAllServicesAreRegisteredInEureka();
    }

    @AfterAll
    static void tearDown(){
        log.info("Acceptance tests execution finished.");
    }

    @Order(1)
    @Test
    void shouldMakeAnOrderAndGetTheOrderedItem(){
        shopMicroserviceWebClient
                .post()
                .uri(InfrastructureConstant.V1_ORDERS)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .bodyValue(
                        """
                        {
                            "item": {
                                "name":"Enormous Paper Bottle"
                            },
                            "customerName":"Brandy Anne Koch",
                            "comment":"715DFD76"
                        }
                        """)
                .exchange()
                .expectStatus()
                .isOk()
                .expectHeader()
                .contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.build.name").isEqualTo("warehouse-microservice")
                .jsonPath("$.build.artifact").isEqualTo("warehouse-microservice");

        // todo: call GET items and verify a product is available
    }
}
