package com.showmeyourcode.spring_cloud.admin;

import com.showmeyourcode.spring_cloud.admin.constant.EndpointConstant;
import io.restassured.RestAssured;
import io.restassured.filter.log.LogDetail;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

class ActuatorEndpointIT extends BaseIT{

    @Test
    void shouldExposeActuatorEndpoint() {
        RestAssured.given(this.requestSpecification)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get(EndpointConstant.ACTUATOR_ENDPOINT)
                .then()
                .assertThat()
                .log().ifValidationFails(LogDetail.BODY)
                .statusCode(Matchers.is(HttpStatus.OK.value()))
                .body("isEmpty()", Matchers.is(false))
                .body("_links", Matchers.notNullValue())
                .body("_links.refresh", Matchers.notNullValue());
    }

    @Test
    void shouldExposeHealthEndpoint() {
        RestAssured.given(this.requestSpecification)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get(EndpointConstant.ACTUATOR_HEALTH_ENDPOINT)
                .then()
                .assertThat()
                .statusCode(Matchers.is(HttpStatus.OK.value()))
                .body("isEmpty()", Matchers.is(false));
    }

    @Test
    void shouldExposeInfoEndpoint() {
        RestAssured.given(this.requestSpecification)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get(EndpointConstant.ACTUATOR_INFO_ENDPOINT)
                .then()
                .assertThat()
                .log().ifValidationFails(LogDetail.BODY)
                .statusCode(Matchers.is(HttpStatus.OK.value()))
                .body("isEmpty()", Matchers.is(false))
                .body("git", Matchers.notNullValue())
                .body("build", Matchers.notNullValue());
    }
}
