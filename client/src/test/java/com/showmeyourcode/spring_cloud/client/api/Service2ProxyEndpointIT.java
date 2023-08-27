package com.showmeyourcode.spring_cloud.client.api;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.showmeyourcode.spring_cloud.client.BaseIT;
import com.showmeyourcode.spring_cloud.client.configuration.ApiPathConstant;
import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.configureFor;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.showmeyourcode.spring_cloud.client.api.Service2ProxyEndpoint.SERVICE2_INTERNAL_PATH;

class Service2ProxyEndpointIT extends BaseIT {

    @Test
    void shouldCallService2UsingRestTemplate() {
        configureFor(wireMockServer.port());
        stubFor(WireMock.get(urlEqualTo(SERVICE2_INTERNAL_PATH))
                .willReturn(aResponse().withStatus(HttpStatus.OK.value()).withBody("microservice2"))
        );

        var uri = ApiPathConstant.SERVICE_2 + Service2ProxyEndpoint.PATH_RESTTEMPLATE;

        RestAssured.given(this.requestSpecification)
                .when()
                .get(uri)
                .then()
                .assertThat()
                .statusCode(Matchers.is(HttpStatus.OK.value()));
    }

    @Test
    void shouldCallService2UsingFeign() {
        configureFor(wireMockServer.port());
        stubFor(WireMock.get(urlEqualTo(SERVICE2_INTERNAL_PATH))
                .willReturn(aResponse().withStatus(HttpStatus.OK.value()).withBody("microservice2"))
        );

        var uri = ApiPathConstant.SERVICE_2 + Service2ProxyEndpoint.PATH_OPENFEIGN;

        RestAssured.given(this.requestSpecification)
                .when()
                .get(uri)
                .then()
                .assertThat()
                .statusCode(Matchers.is(HttpStatus.OK.value()));
    }
}
