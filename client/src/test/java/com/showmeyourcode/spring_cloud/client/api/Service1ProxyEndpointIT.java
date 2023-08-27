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

import static org.springframework.restdocs.restassured3.RestAssuredRestDocumentation.document;

class Service1ProxyEndpointIT extends BaseIT {

    @Test
    void shouldCallService1UsingFeign() {
        configureFor(wireMockServer.port());
        stubFor(WireMock.get(urlEqualTo("/microservice1/api/v1/"))
                .willReturn(aResponse().withStatus(HttpStatus.OK.value()).withBody("microservice1"))
        );

        var uri = ApiPathConstant.SERVICE_1 + Service1ProxyEndpoint.PATH_MANUAL_OPENFEIGN;

        RestAssured.given(this.requestSpecification)
                .filter(document("service1-proxy/getMicroserviceName"))
                .when()
                .get(uri)
                .then()
                .assertThat()
                .statusCode(Matchers.is(HttpStatus.OK.value()));
    }

}
