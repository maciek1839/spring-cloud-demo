package com.showmeyourcode.spring_cloud.demo.reporting.api;

import com.showmeyourcode.spring_cloud.demo.reporting.BaseIT;
import com.showmeyourcode.spring_cloud.demo.reporting.constant.EndpointConstant;
import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;

class ShopProxyEndpointIT extends BaseIT {

    @Test
    void shouldCallShopMicroserviceUsingRestTemplate() {
        wireMockServer.stubFor(
                get(urlEqualTo("/shop/actuator/info"))
                        .willReturn(
                                aResponse()
                                        .withStatus(200)
                                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                                        .withBodyFile("shop/info/success.json")
                        )
        );

        var uri = EndpointConstant.SHOP_MICROSERVICE + WarehouseProxyEndpoint.INFO_PATH;

        RestAssured.given(this.requestSpecification)
                .when()
                .get(addContextPath(uri))
                .then()
                .assertThat()
                .statusCode(Matchers.is(HttpStatus.OK.value()));
    }
}
