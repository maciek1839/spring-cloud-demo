package com.showmeyourcode.spring_cloud.microservice2.api;

import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
public class Microservice2EndpointImpl implements Microservice2Endpoint {

    private final EurekaClient eurekaClient;

    @Value("${spring.application.name}")
    private String appName;

    public Microservice2EndpointImpl(EurekaClient eurekaClient) {
        this.eurekaClient = eurekaClient;
    }

    @Override
    public String getMicroserviceName() {
        return String.format(
                "Name: '%s' Time: '%s'",
                eurekaClient.getApplication(appName).getName(),
                Instant.now());
    }
}
