package com.showmeyourcode.spring_cloud.demo.client.api;

import com.showmeyourcode.spring_cloud.demo.client.BaseIT;
import com.showmeyourcode.spring_cloud.demo.client.constant.EndpointConstant;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

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

}
