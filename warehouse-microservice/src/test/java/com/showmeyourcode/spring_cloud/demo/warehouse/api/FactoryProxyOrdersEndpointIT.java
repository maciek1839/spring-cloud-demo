package com.showmeyourcode.spring_cloud.demo.warehouse.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.showmeyourcode.spring_cloud.demo.warehouse.BaseIT;
import com.showmeyourcode.spring_cloud.demo.warehouse.constant.HttpHeaderConstant;
import com.showmeyourcode.spring_cloud.demo.warehouse.generated.model.NewItemRequest;
import com.showmeyourcode.spring_cloud.demo.warehouse.generated.model.NewOrderRequest;
import io.restassured.RestAssured;
import io.restassured.filter.log.LogDetail;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.delete;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;

class FactoryProxyOrdersEndpointIT extends BaseIT {

    @Test
    void shouldMakeOrder() throws JsonProcessingException {
        wireMockServer.stubFor(
                post(urlEqualTo("/factory/api/v1/orders"))
                        .willReturn(
                                aResponse()
                                        .withStatus(200)
                                        .withHeader(HttpHeaders.LOCATION, "/factory/api/v1/orders/4e82f625-199f-41ee-8243-355d3b0356ca")
                                        .withBodyFile("factory/orders/make/success.json")
                        )
        );

        var uri = FactoryProxyEndpointSpecification.ENDPOINT_PATH+FactoryProxyEndpointSpecification.ORDERS_API_PATH;

        var newOrder = new NewOrderRequest();
        var newItem = new NewItemRequest();
        newItem.setName(faker.commerce().productName());
        newOrder.setComment(faker.random().hex());
        newOrder.setCustomerName(faker.funnyName().name());
        newOrder.setItem(newItem);

        RestAssured.given(this.requestSpecification)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaderConstant.X_CLIENT_ID_HEADER, exampleClientHeaderValue)
                .when()
                .with()
                .body(objectMapper.writeValueAsString(newOrder))
                .post(addContextPath(uri))
                .then()
                .assertThat()
                .statusCode(Matchers.is(HttpStatus.OK.value()));
    }

    @Test
    void shouldCancelOrder() {
        var orderId = "4e82f625-199f-41ee-8243-355d3b0356ca";
        wireMockServer.stubFor(
                delete(urlEqualTo("/factory/api/v1/orders/"+orderId))
                        .willReturn(
                                aResponse()
                                        .withStatus(204)
                        )
        );

        var uri = FactoryProxyEndpointSpecification.ENDPOINT_PATH+FactoryProxyEndpointSpecification.ORDERS_API_PATH+"/"+orderId;

        RestAssured.given(this.requestSpecification)
                .header(HttpHeaderConstant.X_CLIENT_ID_HEADER, exampleClientHeaderValue)
                .when()
                .delete(addContextPath(uri))
                .then()
                .assertThat()
                .statusCode(Matchers.is(HttpStatus.NO_CONTENT.value()));
    }

    @Test
    void shouldGetReport() {
        var uri = FactoryProxyEndpointSpecification.ORDERS_API_PATH+FactoryProxyEndpointSpecification.REPORT_PATH;

        wireMockServer.stubFor(
                get(urlEqualTo("/factory/api/v1/orders/report"))
                        .willReturn(
                                aResponse()
                                        .withStatus(200)
                                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                                        .withBodyFile("factory/orders/report/success.json")
                        )
        );

        RestAssured.given(this.requestSpecification)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaderConstant.X_CLIENT_ID_HEADER, exampleClientHeaderValue)
                .auth().preemptive().basic("user", "user")
                .when()
                .get(addContextPath(uri))
                .then()
                .assertThat()
                .log().ifValidationFails(LogDetail.BODY)
                .statusCode(Matchers.is(HttpStatus.OK.value()));
    }

    @Test
    void shouldNotGetReportWhenUserIsUnauthorized() {
        var uri = FactoryProxyEndpointSpecification.ORDERS_API_PATH+FactoryProxyEndpointSpecification.REPORT_PATH;

        RestAssured.given(this.requestSpecification)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaderConstant.X_CLIENT_ID_HEADER, exampleClientHeaderValue)
                .when()
                .get(addContextPath(uri))
                .then()
                .assertThat()
                .log().ifValidationFails(LogDetail.BODY)
                .statusCode(Matchers.is(HttpStatus.UNAUTHORIZED.value()));
    }
}
