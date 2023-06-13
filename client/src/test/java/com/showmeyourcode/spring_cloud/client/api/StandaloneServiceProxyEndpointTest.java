package com.showmeyourcode.spring_cloud.client.api;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.showmeyourcode.spring_cloud.client.BaseIT;
import com.showmeyourcode.spring_cloud.client.configuration.ApiPathConstant;
import com.showmeyourcode.spring_cloud.client.constant.EndpointConstant;
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

class StandaloneServiceProxyEndpointTest extends BaseIT {

    @Test
    void shouldCallStandaloneServiceUsingRestTemplate() throws URISyntaxException {
        configureFor(wireMockServer.port());
        stubFor(WireMock.get(urlEqualTo("/api/standalone-microservice"))
                .willReturn(aResponse().withStatus(200).withBody("standalone-service-name"))
        );

        var uri = ApiPathConstant.STANDALONE_MANUAL + StandaloneServiceProxyEndpoint.RESTTEMPLATE_ENDPOINT;
        RequestEntity<String> requestEntity = new RequestEntity<>(HttpMethod.GET, new URI(uri));
        ResponseEntity<String> responseEntity = restTemplate.exchange(requestEntity, String.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
    }
}