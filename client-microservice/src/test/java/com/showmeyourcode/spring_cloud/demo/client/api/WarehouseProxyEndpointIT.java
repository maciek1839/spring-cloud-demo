package com.showmeyourcode.spring_cloud.demo.client.api;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.showmeyourcode.spring_cloud.demo.client.BaseIT;
import com.showmeyourcode.spring_cloud.demo.client.constant.EndpointConstant;
import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.configureFor;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;

class WarehouseProxyEndpointIT extends BaseIT {

    @Test
    void shouldCallWarehouseMicroserviceUsingRestTemplate() {
        wireMockServer.stubFor(
                get(urlEqualTo("/warehouse/actuator/info"))
                        .willReturn(
                                aResponse()
                                        .withStatus(200)
                                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                                        .withBodyFile("warehouse/info/success.json")
                        )
        );

        var uri = EndpointConstant.WAREHOUSE_MICROSERVICE + WarehouseProxyEndpoint.INFO_PATH;

        RestAssured.given(this.requestSpecification)
                .when()
                .get(addContextPath(uri))
                .then()
                .assertThat()
                .statusCode(Matchers.is(HttpStatus.OK.value()));
    }
}
