package com.showmeyourcode.spring_cloud.client.api;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.showmeyourcode.spring_cloud.client.BaseIT;
import com.showmeyourcode.spring_cloud.client.configuration.ApiPathConstant;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.net.URISyntaxException;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.configureFor;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class Service1ProxyEndpointTest extends BaseIT {

    @Test
    void shouldCallService1UsingFeign() throws URISyntaxException {
        configureFor(wireMockServer.port());
        stubFor(WireMock.get(urlEqualTo("/microservice1/api/v1/"))
                .willReturn(aResponse().withStatus(200).withBody("microservice1"))
        );

        var uri = ApiPathConstant.SERVICE_1 + Service1ProxyEndpoint.PATH_MANUAL_OPENFEIGN;
        RequestEntity<String> requestEntity = new RequestEntity<>(HttpMethod.GET, new URI(uri));
        ResponseEntity<String> responseEntity = restTemplate.exchange(requestEntity, String.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
    }
}
