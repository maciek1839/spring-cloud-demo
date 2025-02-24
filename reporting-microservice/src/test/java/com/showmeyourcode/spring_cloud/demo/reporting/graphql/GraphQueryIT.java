package com.showmeyourcode.spring_cloud.demo.reporting.graphql;

import com.showmeyourcode.spring_cloud.demo.reporting.BaseIT;
import com.showmeyourcode.spring_cloud.demo.reporting.constant.EndpointConstant;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;

class GraphQueryIT extends BaseIT {

    private final GraphQLUtil graphQLUtil = new GraphQLUtil();

    @Test
    void shouldGetShopItems() {
        wireMockServer.stubFor(
                get(urlEqualTo("/shop/api/v1/items"))
                        .willReturn(
                                aResponse()
                                        .withStatus(200)
                                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                                        .withBodyFile("shop/items/get-all.json")
                        )
        );

        RestAssured.given(this.requestSpecification)
                .contentType(ContentType.JSON)
                .when()
                .body(graphQLUtil.getShopItemsPayload)
                .post(addContextPath(EndpointConstant.GRAPHQL_ROOT))
                .then()
                .assertThat()
                .statusCode(Matchers.is(HttpStatus.OK.value()))
                .body("data.getShopItems.size()", Matchers.is(25))
                .body("$", Matchers.not(Matchers.hasKey("errors")));
    }

    @Test
    void shouldHandleErrorsWhenGettingShopItems() {
        wireMockServer.stubFor(
                get(urlEqualTo("/shop/api/v1/items"))
                        .willReturn(
                                aResponse()
                                        .withStatus(503)
                                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        )
        );

        RestAssured.given(this.requestSpecification)
                .contentType(ContentType.JSON)
                .when()
                .body(graphQLUtil.getShopItemsPayload)
                .post(addContextPath(EndpointConstant.GRAPHQL_ROOT))
                .then()
                .assertThat()
                .statusCode(Matchers.is(HttpStatus.OK.value()))
                .body("errors.size()", Matchers.is(1))
                .body("errors[0].message", Matchers.containsString("INTERNAL_ERROR"));
    }

    @Test
    void shouldGetWarehouseItems() {
        wireMockServer.stubFor(
                get(urlEqualTo("/warehouse/api/v1/items"))
                        .willReturn(
                                aResponse()
                                        .withStatus(200)
                                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                                        .withBodyFile("warehouse/items/get-all.json")
                        )
        );

        RestAssured.given(this.requestSpecification)
                .contentType(ContentType.JSON)
                .when()
                .body(graphQLUtil.getWarehouseItemsPayload)
                .post(addContextPath(EndpointConstant.GRAPHQL_ROOT))
                .then()
                .assertThat()
                .statusCode(Matchers.is(HttpStatus.OK.value()))
                .body("data.getWarehouseItems.size()", Matchers.is(25))
                .body("$", Matchers.not(Matchers.hasKey("errors")));
    }

    @Test
    void shouldValidateQueryAndReturnErrorWhenQueryIsInvalid() {
        RestAssured.given(this.requestSpecification)
                .accept(MediaType.TEXT_PLAIN_VALUE)
                .when()
                .body(graphQLUtil.getNotExistingQuery)
                .post(addContextPath(EndpointConstant.GRAPHQL_ROOT))
                .then()
                .assertThat()
                .statusCode(Matchers.is(HttpStatus.NOT_FOUND.value()));
    }
}
