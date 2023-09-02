package com.showmeyourcode.spring_cloud.demo.factory.api;

import com.showmeyourcode.spring_cloud.demo.factory.BaseIT;
import com.showmeyourcode.spring_cloud.demo.factory.constant.HttpHeaderConstant;
import com.showmeyourcode.spring_cloud.demo.factory.model.NewItem;
import io.restassured.RestAssured;
import io.restassured.filter.log.LogDetail;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import reactor.test.StepVerifier;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

import static org.hamcrest.Matchers.is;

class ItemsEndpointIT extends BaseIT {

    @Test
    void shouldGetAllItems() {
        StepVerifier
                .create(itemsService.addItem(new NewItem(faker.book().title())))
                .expectNextCount(1L)
                .expectComplete()
                .verify();
        StepVerifier
                .create(itemsService.addItem(new NewItem(faker.book().title())))
                .expectNextCount(1L)
                .expectComplete()
                .verify();

        RestAssured.given(this.requestSpecification)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaderConstant.X_CLIENT_ID_HEADER, exampleClientHeaderValue)
                .when()
                .get(addContextPath(ItemsEndpointSpecification.ENDPOINT_PATH))
                .then()
                .assertThat()
                .log().ifValidationFails(LogDetail.BODY)
                .statusCode(is(HttpStatus.OK.value()))
                .body("size()", Matchers.is(2));
    }

    @Test
    void shouldGetBadRequestErrorWhenHeaderIsNotPresentForAllItemsRequest() {
        RestAssured.given(this.requestSpecification)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get(addContextPath(ItemsEndpointSpecification.ENDPOINT_PATH))
                .then()
                .assertThat()
                .log().ifValidationFails(LogDetail.BODY)
                .statusCode(is(HttpStatus.BAD_REQUEST.value()));
    }

    @Test
    void shouldGetItemById() {
        AtomicReference<UUID> itemId = new AtomicReference<>();
        var newItem = new NewItem(faker.book().title());
        StepVerifier
                .create(itemsService.addItem(newItem))
                .consumeNextWith(itemId::set)
                .expectComplete()
                .verify();

        RestAssured.given(this.requestSpecification)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaderConstant.X_CLIENT_ID_HEADER, exampleClientHeaderValue)
                .when()
                .get(addContextPath(ItemsEndpointSpecification.ENDPOINT_PATH+"/"+itemId))
                .then()
                .assertThat()
                .log().ifValidationFails(LogDetail.BODY)
                .statusCode(is(HttpStatus.OK.value()))
                .body("name", Matchers.is(newItem.name()));
    }

    @Test
    void shouldNotFindItemWhichDoesNotExist() {
        var id = UUID.randomUUID();

        RestAssured.given(this.requestSpecification)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaderConstant.X_CLIENT_ID_HEADER, exampleClientHeaderValue)
                .when()
                .get(addContextPath(ItemsEndpointSpecification.ENDPOINT_PATH+"/"+id))
                .then()
                .assertThat()
                .log().ifValidationFails(LogDetail.BODY)
                .statusCode(is(HttpStatus.NOT_FOUND.value()));
    }

    @Test
    void shouldGetBadRequestErrorWhenHeaderIsNotPresentForGetItemsRequest() {
        RestAssured.given(this.requestSpecification)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get(addContextPath(ItemsEndpointSpecification.ENDPOINT_PATH+"/123"))
                .then()
                .assertThat()
                .log().ifValidationFails(LogDetail.BODY)
                .statusCode(is(HttpStatus.BAD_REQUEST.value()));
    }
}
