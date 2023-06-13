package com.showmeyourcode.spring_cloud.microservice2.api;

import com.netflix.discovery.shared.Application;
import com.showmeyourcode.spring_cloud.microservice2.BaseIT;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;

class Microservice2EndpointTest extends BaseIT {

    @Test
    void shouldGetMicroserviceName() throws URISyntaxException {
        Mockito.when(eurekaClient.getApplication(any())).thenReturn(new Application("test-name-app"));

        String endpointUri = Microservice2Endpoint.ENDPOINT_PATH;
        RequestEntity<String> requestEntity = new RequestEntity<>(HttpMethod.GET, new URI(endpointUri));
        ResponseEntity<String> responseEntity = restTemplate.exchange(requestEntity, String.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("text/plain;charset=UTF-8", responseEntity.getHeaders().getContentType().toString());
        assertNotNull(responseEntity.getBody());
        assertTrue(responseEntity.getBody().startsWith("Name: 'test-name-app' Time"));
    }
}
