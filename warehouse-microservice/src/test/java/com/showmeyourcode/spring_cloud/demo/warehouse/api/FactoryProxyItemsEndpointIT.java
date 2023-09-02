package com.showmeyourcode.spring_cloud.demo.warehouse.api;

import com.showmeyourcode.spring_cloud.demo.warehouse.BaseIT;
import com.showmeyourcode.spring_cloud.demo.warehouse.constant.HttpHeaderConstant;
import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.UUID;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;

class FactoryProxyItemsEndpointIT extends BaseIT {

    @Test
    void shouldGetAllItems() {
        wireMockServer.stubFor(
                get(urlEqualTo("/factory/api/v1/items"))
                        .willReturn(
                                aResponse()
                                        .withStatus(200)
                                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                                        .withBodyFile("factory/items/get-all/success.json")
                        )
        );

        var uri = FactoryProxyEndpointSpecification.ENDPOINT_PATH+FactoryProxyEndpointSpecification.ITEMS_API_PATH;

        RestAssured.given(this.requestSpecification)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaderConstant.X_CLIENT_ID_HEADER, exampleClientHeaderValue)
                .when()
                .get(addContextPath(uri))
                .then()
                .assertThat()
                .statusCode(Matchers.is(HttpStatus.OK.value()));
    }

    @Test
    void shouldGetItemById() {
        var itemId = UUID.fromString("550e8400-e29b-41d4-a716-446655440000");
        wireMockServer.stubFor(
                get(urlEqualTo("/factory/api/v1/items/550e8400-e29b-41d4-a716-446655440000"))
                .willReturn(
                        aResponse()
                                .withStatus(200)
                                .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                                .withBodyFile("factory/items/get-by-id/success.json")
                )
        );

        var uri = FactoryProxyEndpointSpecification.ENDPOINT_PATH+FactoryProxyEndpointSpecification.ITEMS_API_PATH+"/"+itemId;

        RestAssured.given(this.requestSpecification)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaderConstant.X_CLIENT_ID_HEADER, exampleClientHeaderValue)
                .when()
                .get(addContextPath(uri))
                .then()
                .assertThat()
                .statusCode(Matchers.is(HttpStatus.OK.value()));
    }
}
