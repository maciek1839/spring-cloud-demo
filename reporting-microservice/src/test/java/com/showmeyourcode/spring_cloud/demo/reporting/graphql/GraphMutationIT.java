package com.showmeyourcode.spring_cloud.demo.reporting.graphql;

import com.showmeyourcode.spring_cloud.demo.reporting.BaseIT;
import com.showmeyourcode.spring_cloud.demo.reporting.constant.EndpointConstant;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;

class GraphMutationIT extends BaseIT {

    private final GraphQLUtil graphQLUtil = new GraphQLUtil();

    @Test
    void shouldMakeShopOrder() {
        wireMockServer.stubFor(
                post(urlEqualTo("/shop/api/v1/orders"))
                        .willReturn(
                                aResponse()
                                        .withStatus(201)
                        )
        );

        RestAssured.given(this.requestSpecification)
                .contentType(ContentType.JSON)
                .when()
                .body(graphQLUtil.mutateShopOrder)
                .post(addContextPath(EndpointConstant.GRAPHQL_ROOT))
                .then()
                .assertThat()
                .statusCode(Matchers.is(HttpStatus.OK.value()))
                .body("$", Matchers.not(Matchers.hasKey("errors")))
                .body("data.makeShopOrder", Matchers.is(201));
    }

    @Test
    void shouldHandleErrorsWhenMakingShopOrder() {
        wireMockServer.stubFor(
                post(urlEqualTo("/shop/api/v1/orders"))
                        .willReturn(
                                aResponse()
                                        .withStatus(400)
                        )
        );

        RestAssured.given(this.requestSpecification)
                .contentType(ContentType.JSON)
                .when()
                .body(graphQLUtil.mutateShopOrder)
                .post(addContextPath(EndpointConstant.GRAPHQL_ROOT))
                .then()
                .assertThat()
                .statusCode(Matchers.is(HttpStatus.OK.value()))
                .body("errors.size()", Matchers.is(1))
                .body("errors[0].message", Matchers.containsString("INTERNAL_ERROR"));
    }
}
