package com.showmeyourcode.spring_cloud.standalone_service.api;

import com.showmeyourcode.spring_cloud.standalone_service.BaseIT;
import io.restassured.RestAssured;
import io.restassured.filter.log.LogDetail;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

class StandaloneEndpointIT extends BaseIT {

    @Test
    void shouldGetMicroserviceName() {
        String endpointUri = addContextPath(StandaloneEndpoint.ENDPOINT_PATH);

        Response response = RestAssured.given(this.requestSpecification)
                .when()
                .get(endpointUri);

        MatcherAssert.assertThat(response.statusCode(), Matchers.is(HttpStatus.OK.value()));
        MatcherAssert.assertThat(response.contentType(), Matchers.is("text/plain;charset=UTF-8"));
        MatcherAssert.assertThat(response.body().asString().startsWith("Name: 'standalone-service' Time"), Matchers.is(true));
    }

    @Test
    void shouldGetProperties() {
        RestAssured.given(this.requestSpecification)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get(addContextPath(StandaloneEndpoint.ENDPOINT_PROPS_PATH))
                .then()
                .assertThat()
                .log().ifValidationFails(LogDetail.BODY)
                .statusCode(Matchers.is(HttpStatus.OK.value()));
    }

    @Test
    void shouldDeleteProperty() {
        var uri = StandaloneEndpoint.ENDPOINT_PROPS_PATH+"/60f5f94d-1ed8-43a2-bc39-5fdc750bcfb5";

        RestAssured.given(this.requestSpecification)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .delete(addContextPath(uri))
                .then()
                .assertThat()
                .statusCode(Matchers.is(HttpStatus.NO_CONTENT.value()));
    }
}
