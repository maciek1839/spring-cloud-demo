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
import java.util.concurrent.atomic.AtomicReference;

@Slf4j
@RequiredArgsConstructor
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AcceptanceTests {

    private static final InfrastructureVerifier infrastructureVerifier = new InfrastructureVerifier();

    private final WebTestClient shopMicroserviceWebClient = TestHelper.shopMicroserviceWebClient();

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
    void shouldMakeAnOrderAndCancelSuccessfully() {
        final AtomicReference<String> location = new AtomicReference<>();

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
                .isCreated()
                .expectHeader()
                .value(HttpHeaders.LOCATION, header ->{
                    log.info("A new item location: {}", header);
                    location.set(header);
                })
                .expectBody()
                .isEmpty();

        var locationWithoutMicroservicePrefix = location.get().replace("/shop","");
        log.info("Cancelling an order: {} (original: {})",
                locationWithoutMicroservicePrefix,
                location.get()
        );

        shopMicroserviceWebClient
                .delete()
                .uri(locationWithoutMicroservicePrefix)
                .exchange()
                .expectStatus()
                .isNoContent()
                .expectBody()
                .isEmpty();
    }
}
