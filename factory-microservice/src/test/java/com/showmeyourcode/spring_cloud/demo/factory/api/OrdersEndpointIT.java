package com.showmeyourcode.spring_cloud.demo.factory.api;

import com.showmeyourcode.spring_cloud.demo.factory.BaseIT;
import com.showmeyourcode.spring_cloud.demo.factory.constant.HttpHeaderConstant;
import com.showmeyourcode.spring_cloud.demo.factory.model.NewOrderRequest;
import com.showmeyourcode.spring_cloud.demo.factory.model.NewItemRequest;
import io.restassured.RestAssured;
import io.restassured.filter.log.LogDetail;
import lombok.SneakyThrows;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import reactor.test.StepVerifier;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

import static org.hamcrest.Matchers.is;
import static org.springframework.restdocs.restassured3.RestAssuredRestDocumentation.document;

class OrdersEndpointIT extends BaseIT {

    @Test
    @SneakyThrows
    void shouldAddNewOrderAndProcessedIt() {
        var newOrder = new NewOrderRequest(
                new NewItemRequest(faker.commerce().productName()),
                faker.funnyName().name(),
                faker.random().hex()
        );
        RestAssured.given(this.requestSpecification)
                .filter(document("orders/make"))
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaderConstant.X_CLIENT_ID_HEADER, exampleClientHeaderValue)
                .when()
                .with()
                .body(objectMapper.writeValueAsString(newOrder))
                .post(addContextPath(OrdersEndpointSpecification.ENDPOINT_PATH))
                .then()
                .assertThat()
                .log().ifValidationFails(LogDetail.BODY)
                .statusCode(is(HttpStatus.CREATED.value()))
                .header(HttpHeaders.LOCATION, Matchers.notNullValue());

        // todo: try https://stackoverflow.com/questions/59029446/java-reactor-stepverifier-withvirtualtime-loop-repeatedly-check-with-expectnoe
        // .withVirtualTime(() -> Flux.interval(Duration.ofSeconds(1)).take(3600))
        // verify an item was added and the status changed
    }

    @Test
    void shouldCancelNotProcessedOrder() {
        AtomicReference<UUID> orderId = new AtomicReference<>();
        var newOrder = new NewOrderRequest(
                new NewItemRequest(faker.commerce().productName()),
                faker.funnyName().name(),
                faker.random().hex()
        );
        StepVerifier
                .create(ordersService.create(newOrder))
                .consumeNextWith(orderId::set)
                .expectComplete()
                .verify();

        RestAssured.given(this.requestSpecification)
                .filter(document("orders/cancel"))
                .header(HttpHeaderConstant.X_CLIENT_ID_HEADER, exampleClientHeaderValue)
                .when()
                .delete(addContextPath(OrdersEndpointSpecification.ENDPOINT_PATH+"/"+orderId))
                .then()
                .assertThat()
                .log().ifValidationFails(LogDetail.BODY)
                .statusCode(Matchers.is(HttpStatus.NO_CONTENT.value()));
    }

    @Test
    void shouldNotCancelNotExistingOrder() {
        RestAssured.given(this.requestSpecification)
                .header(HttpHeaderConstant.X_CLIENT_ID_HEADER, exampleClientHeaderValue)
                .when()
                .delete(addContextPath(OrdersEndpointSpecification.ENDPOINT_PATH+"/"+UUID.randomUUID()))
                .then()
                .assertThat()
                .log().ifValidationFails(LogDetail.BODY)
                .statusCode(Matchers.is(HttpStatus.NOT_FOUND.value()));
    }

// todo: add missing tests
//    @Test
//    void shouldNotCancelAlreadyProcessedOrder() {
//
//    }

    @Test
    void shouldGetReport() {
        var uri = OrdersEndpointSpecification.ENDPOINT_PATH+OrdersEndpointSpecification.REPORT_PATH;

        RestAssured.given(this.requestSpecification)
                .filter(document("orders/report"))
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
        var uri = OrdersEndpointSpecification.ENDPOINT_PATH+OrdersEndpointSpecification.REPORT_PATH;

        RestAssured.given(this.requestSpecification)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get(addContextPath(uri))
                .then()
                .assertThat()
                .log().ifValidationFails(LogDetail.BODY)
                .statusCode(Matchers.is(HttpStatus.UNAUTHORIZED.value()));
    }
}
