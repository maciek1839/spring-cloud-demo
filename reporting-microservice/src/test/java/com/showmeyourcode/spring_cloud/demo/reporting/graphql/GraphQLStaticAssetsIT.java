package com.showmeyourcode.spring_cloud.demo.reporting.graphql;

import com.showmeyourcode.spring_cloud.demo.reporting.BaseIT;
import com.showmeyourcode.spring_cloud.demo.reporting.constant.EndpointConstant;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

class GraphQLStaticAssetsIT extends BaseIT {

    @ParameterizedTest
    @ValueSource(strings = {
            EndpointConstant.GRAPHQL_ALTAIR,
            EndpointConstant.GRAPHQL_GRAPHIQL,
            EndpointConstant.GRAPHQL_VOYAGER,
            EndpointConstant.GRAPHQL_PLAYGROUND
    })
    void shouldGetStaticFile(String path) {
        Response response = RestAssured.given(this.requestSpecification)
                .accept(MediaType.TEXT_HTML_VALUE)
                .when()
                .get(addContextPath(path));

        MatcherAssert.assertThat(response.statusCode(), Matchers.is(HttpStatus.OK.value()));
        MatcherAssert.assertThat(response.body().asString().isBlank(), Matchers.is(false));
    }

}
