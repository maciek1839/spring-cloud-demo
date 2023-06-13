package com.showmeyourcode.spring_cloud.microservice2.api;


import com.showmeyourcode.spring_cloud.microservice2.BaseIT;
import com.showmeyourcode.spring_cloud.microservice2.constant.EndpointConstant;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class SwaggerEndpointTest extends BaseIT {

    @Test
    void shouldExposeSwaggerEndpoint() throws URISyntaxException {
        RequestEntity<String> requestEntity = new RequestEntity<>(HttpMethod.GET, new URI(EndpointConstant.SWAGGER_UI));
        ResponseEntity<String> responseEntity = restTemplate.exchange(requestEntity, String.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
    }
}
