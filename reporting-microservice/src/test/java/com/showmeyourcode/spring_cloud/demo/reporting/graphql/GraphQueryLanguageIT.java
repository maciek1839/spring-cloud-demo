package com.showmeyourcode.spring_cloud.demo.reporting.graphql;

import com.showmeyourcode.spring_cloud.demo.reporting.BaseIT;
import com.showmeyourcode.spring_cloud.demo.reporting.constant.EndpointConstant;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

class GraphQueryLanguageIT extends BaseIT {

    private final GraphQLUtil graphQLUtil = new GraphQLUtil();

    @Test
    void shouldGetServicesOverview() {
        RestAssured.given(this.requestSpecification)
                .contentType(ContentType.JSON)
                .when()
                .body(graphQLUtil.buildQuery(loadFileFromResources("graphql/examples/defaultQuery.graphql")))
                .post(addContextPath(EndpointConstant.GRAPHQL_ROOT))
                .then()
                .assertThat()
                .statusCode(Matchers.is(HttpStatus.OK.value()))
                .body("data.getServicesInfo.shop", Matchers.is("sample"))
                .body("$", Matchers.not(Matchers.hasKey("errors")));
    }

    @Test
    void shouldAddItem() {
        RestAssured.given(this.requestSpecification)
                .contentType(ContentType.JSON)
                .when()
                .body(graphQLUtil.buildQuery(loadFileFromResources("graphql/examples/defaultMutation.graphql")))
                .post(addContextPath(EndpointConstant.GRAPHQL_ROOT))
                .then()
                .assertThat()
                .statusCode(Matchers.is(HttpStatus.OK.value()));
    }

    @Test
    void shouldValidateQueryAndReturnErrorWhenQueryIsInvalid() {
        RestAssured.given(this.requestSpecification)
                .accept(MediaType.TEXT_HTML_VALUE)
                .when()
                .body(graphQLUtil.buildQuery(graphQLUtil.buildWrongMutation()))
                .post(addContextPath(EndpointConstant.GRAPHQL_ROOT))
                .then()
                .assertThat()
                .statusCode(Matchers.is(HttpStatus.OK.value()))
                .body("errors.size()", Matchers.is(1))
                .body("errors[0].message", Matchers.is("Validation error (FieldUndefined@[doesNotExist]) : Field 'doesNotExist' in type 'Mutation' is undefined"));
    }
}
