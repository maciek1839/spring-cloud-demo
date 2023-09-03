package com.showmeyourcode.spring_cloud.demo.test;

import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

class InfrastructureVerifier {

    private final WebTestClient clientMicroserviceWebClient = TestHelper.clientMicroserviceWebClient();
    private final WebTestClient shopMicroserviceWebClient = TestHelper.shopMicroserviceWebClient();
    private final WebTestClient factoryMicroserviceWebClient = TestHelper.factoryMicroserviceWebClient();
    private final WebTestClient warehouseMicroserviceWebClient = TestHelper.warehouseMicroserviceWebClient();
    private final WebTestClient eurekaMicroserviceWebClient = TestHelper.eurekaMicroserviceWebClient();
    private final WebTestClient adminMicroserviceWebClient = TestHelper.adminMicroserviceWebClient();

    public void verifyAllServicesArUp(){
        verifyMicroserviceIsUp(clientMicroserviceWebClient);
        verifyMicroserviceIsUp(shopMicroserviceWebClient);
        verifyMicroserviceIsUp(factoryMicroserviceWebClient);
        verifyMicroserviceIsUp(warehouseMicroserviceWebClient);
        verifyMicroserviceIsUp(eurekaMicroserviceWebClient);
        verifyMicroserviceIsUp(adminMicroserviceWebClient);
    }

    private void verifyMicroserviceIsUp(WebTestClient webClient){
        webClient
                .get()
                .uri(InfrastructureConstant.HEALTH_ENDPOINT)
                .exchange()
                .expectStatus()
                .isOk()
                .expectHeader()
                .contentType("application/vnd.spring-boot.actuator.v3+json")
                .expectBody()
                .jsonPath("$.status").isEqualTo("UP");
    }

    public void verifyAllServicesAreRegisteredInEureka(){
        eurekaMicroserviceWebClient
                .get()
                .uri(InfrastructureConstant.EUREKA_APPS)
                .exchange()
                .expectStatus()
                .isOk()
                .expectHeader()
                .contentType(MediaType.APPLICATION_XML)
                .expectBody()
                .consumeWith(System.out::println)
                .xpath("/applications/application").nodeCount(5);
    }
}
