package com.showmeyourcode.spring_cloud.microservice2.api;

import com.netflix.discovery.shared.Application;
import com.showmeyourcode.spring_cloud.microservice2.BaseIT;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;

import java.net.URISyntaxException;
import static org.mockito.ArgumentMatchers.any;

class Microservice2EndpointIT extends BaseIT {

    @Test
    void shouldGetMicroserviceName() {
        Mockito.when(eurekaClient.getApplication(any())).thenReturn(new Application("test-name-app"));

        String endpointUri = addContextPath(Microservice2Endpoint.ENDPOINT_PATH);

        Response response = RestAssured.given(this.requestSpecification)
                .when()
                .get(endpointUri);

        MatcherAssert.assertThat(response.statusCode(), Matchers.is(HttpStatus.OK.value()));
        MatcherAssert.assertThat(response.contentType(), Matchers.is("text/plain;charset=UTF-8"));
        MatcherAssert.assertThat(response.body().asString().startsWith("Name: 'test-name-app' Time"), Matchers.is(true));
    }
}
