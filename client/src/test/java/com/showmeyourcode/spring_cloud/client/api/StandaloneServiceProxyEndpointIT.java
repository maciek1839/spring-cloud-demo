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

class StandaloneServiceProxyEndpointIT extends BaseIT {

    @Test
    void shouldCallStandaloneServiceUsingRestTemplate() {
        configureFor(wireMockServer.port());
        stubFor(WireMock.get(urlEqualTo("/api/v1"))
                .willReturn(aResponse().withStatus(HttpStatus.OK.value()).withBody("standalone-service-name"))
        );

        var uri = ApiPathConstant.STANDALONE_MANUAL + StandaloneServiceProxyEndpoint.RESTTEMPLATE_ENDPOINT;

        RestAssured.given(this.requestSpecification)
                .when()
                .get(uri)
                .then()
                .assertThat()
                .statusCode(Matchers.is(HttpStatus.OK.value()));
    }
}
