package com.showmeyourcode.spring_cloud.demo.admin;

import com.showmeyourcode.spring_cloud.demo.admin.constant.EndpointConstant;
import io.restassured.RestAssured;
import io.restassured.filter.log.LogDetail;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.Arrays;

class AdminApplicationIT extends BaseIT {

    @Test
    void shouldExposeAdminUi() {
        RestAssured.given(this.requestSpecification)
                .accept(MediaType.TEXT_HTML_VALUE)
                .when()
                .get(EndpointConstant.ADMIN_UI)
                .then()
                .assertThat()
                .log().ifValidationFails(LogDetail.BODY)
                .statusCode(Matchers.is(HttpStatus.OK.value()));
    }

    @Test
    void shouldStartAdminServerWithoutErrors() {
        // For integration tests Eureka discovery is disabled (see application-test.yml).
        MatcherAssert.assertThat(context.containsBean("discoveryClient"), Matchers.is(false));

        // Ensure that cloud components are created.
        // It's also a good way to investigate what beans were created automatically in case of debugging any configuration errors.
        MatcherAssert.assertThat(
                Arrays.stream(context.getBeanDefinitionNames()).filter(e->e.contains("cloud")).count(),
                Matchers.greaterThan(0L)
        );
    }
}
