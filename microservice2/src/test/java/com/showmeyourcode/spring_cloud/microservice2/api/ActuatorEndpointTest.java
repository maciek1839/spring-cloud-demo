package com.showmeyourcode.spring_cloud.microservice2.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.showmeyourcode.spring_cloud.microservice2.BaseIT;
import com.showmeyourcode.spring_cloud.microservice2.constant.EndpointConstant;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
class ActuatorEndpointTest extends BaseIT {

    @Test
    void shouldExposeActuatorEndpoint() throws URISyntaxException {
        RequestEntity<String> requestEntity = new RequestEntity<>(HttpMethod.GET, new URI(EndpointConstant.ACTUATOR_ENDPOINT));
        ResponseEntity<String> responseEntity = restTemplate.exchange(requestEntity, String.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void shouldExposeHealthEndpoint() throws URISyntaxException {
        RequestEntity<String> requestEntity = new RequestEntity<>(HttpMethod.GET, new URI(EndpointConstant.ACTUATOR_HEALTH_ENDPOINT));
        ResponseEntity<String> responseEntity = restTemplate.exchange(requestEntity, String.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
    }

    @Test
    void shouldExposeInfoEndpoint() throws URISyntaxException, JsonProcessingException {
        RequestEntity<String> requestEntity = new RequestEntity<>(HttpMethod.GET, new URI(EndpointConstant.ACTUATOR_INFO_ENDPOINT));
        ResponseEntity<String> responseEntity = restTemplate.exchange(requestEntity, String.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());

        JsonNode infoNode = new ObjectMapper().readTree(responseEntity.getBody());
        assertNotNull(infoNode.get("git"));
        assertNotNull(infoNode.get("build"));
    }

    @Test
    void shouldExposeMetricsEndpoint() throws URISyntaxException, JsonProcessingException {
        RequestEntity<String> requestEntity = new RequestEntity<>(HttpMethod.GET, new URI(EndpointConstant.ACTUATOR_METRICS_ENDPOINT));
        ResponseEntity<String> responseEntity = restTemplate.exchange(requestEntity, String.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());

        var mapper = new ObjectMapper();
        JsonNode metricsNode = mapper.readTree(responseEntity.getBody());
        assertNotNull(metricsNode.get("names"));
        var actualMetrics = mapper.convertValue(metricsNode.get("names"), ArrayList.class);
        log.info("{}",actualMetrics);
        assertThat(actualMetrics).contains(
            "jvm.buffer.count",
            "jvm.buffer.memory.used",
            "jvm.buffer.total.capacity",
            "jvm.classes.loaded",
            "jvm.classes.unloaded",
            "jvm.gc.live.data.size",
            "jvm.gc.max.data.size",
            "jvm.gc.memory.allocated",
            "jvm.gc.memory.promoted",
            "jvm.gc.pause",
            "jvm.memory.committed",
            "jvm.memory.max",
            "jvm.memory.used",
            "jvm.threads.daemon",
            "jvm.threads.live",
            "jvm.threads.peak",
            "jvm.threads.states",
            "logback.events",
            "process.cpu.usage",
            "process.start.time",
            "process.uptime",
            "system.cpu.count",
            "system.cpu.usage",
            "tomcat.sessions.active.current",
            "tomcat.sessions.active.max",
            "tomcat.sessions.alive.max",
            "tomcat.sessions.created",
            "tomcat.sessions.expired",
            "tomcat.sessions.rejected"
        );
    }
}
