package com.showmeyourcode.spring_cloud.client.dev.service;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.springframework.http.HttpStatus;

public class Service1Mocks {

    public static void setupMockService1Response(WireMockServer mockService) {
        mockService.stubFor(WireMock.get(WireMock.urlEqualTo("/api/microservice1"))
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.OK.value())
//                            .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBody("EXAMPLE-RESPONSE-SERVICE1-MOCK")));
    }
}
