package com.showmeyourcode.spring_cloud.standalone_service.api;

import com.showmeyourcode.spring_cloud.standalone_service.BaseIT;
import com.showmeyourcode.spring_cloud.standalone_service.model.StandaloneModel1;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.net.URISyntaxException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;

class StandaloneEndpointIT extends BaseIT {

    @Test
    void shouldGetMicroserviceName() throws URISyntaxException {
        RequestEntity<String> requestEntity = new RequestEntity<>(HttpMethod.GET, new URI(StandaloneEndpoint.ENDPOINT_PATH));
        ResponseEntity<String> responseEntity = restTemplate.exchange(requestEntity, String.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("text/plain;charset=UTF-8", responseEntity.getHeaders().getContentType().toString());
        assertNotNull(responseEntity.getBody());
        assertTrue(responseEntity.getBody().startsWith("Name standalone service: 'standalone-service' Time:"));
    }

    @Test
    void shouldGetProperties() throws URISyntaxException {
        RequestEntity<StandaloneModel1[]> requestEntity = new RequestEntity<>(HttpMethod.GET, new URI(StandaloneEndpoint.ENDPOINT_PROPS_PATH));
        ResponseEntity<StandaloneModel1[]> responseEntity = restTemplate.exchange(requestEntity, StandaloneModel1[].class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("application/json", responseEntity.getHeaders().getContentType().toString());
        assertNotNull(responseEntity.getBody());
        assertThat(responseEntity.getBody()).isEmpty();
    }

    @Test
    void shouldDeleteProperty() throws URISyntaxException {
        var uri = StandaloneEndpoint.ENDPOINT_PROPS_PATH+"/60f5f94d-1ed8-43a2-bc39-5fdc750bcfb5";
        RequestEntity<String> requestEntity = new RequestEntity<>(HttpMethod.DELETE, new URI(uri));
        ResponseEntity<String> responseEntity = restTemplate.exchange(requestEntity, String.class);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }
}
