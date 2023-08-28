package com.showmeyourcode.spring_cloud.microservice1.api;

import com.showmeyourcode.spring_cloud.microservice1.BaseIT;
import com.showmeyourcode.spring_cloud.microservice1.constant.EndpointConstant;
import com.showmeyourcode.spring_cloud.test.util.ApiSchemaUtil;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.io.IOException;

@Slf4j
class SwaggerEndpointIT extends BaseIT {

    @Test
    void shouldExposeSwaggerEndpoint() {
        Response response = RestAssured.given(this.requestSpecification)
                .accept(MediaType.TEXT_HTML_VALUE)
                .when()
                .get(addContextPath(EndpointConstant.SWAGGER_UI));

        MatcherAssert.assertThat(response.statusCode(), Matchers.is(HttpStatus.OK.value()));
        MatcherAssert.assertThat(response.body().asString().isBlank(), Matchers.is(false));
    }

    // Update client's schema. This was done in order to always keep the latest schema version
    // and easily compare changes without manually copy and paste.
    @Test
    void shouldUpdateClientSchemaInAnotherMavenModule() throws IOException {
        Response response = RestAssured.given(this.requestSpecification)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get(addContextPath(EndpointConstant.SWAGGER_API_DOC));

        MatcherAssert.assertThat(response.statusCode(), Matchers.is(HttpStatus.OK.value()));
        MatcherAssert.assertThat(response.body().asString().isBlank(), Matchers.is(false));

        ApiSchemaUtil.updateClientSchema(response.body().asString(), "api-docs-microservice1.json");
    }
}
