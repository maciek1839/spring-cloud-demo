package com.showmeyourcode.spring_cloud.microservice1.api;

import com.netflix.discovery.EurekaClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@Slf4j
@RequiredArgsConstructor
@RestController
public class Microservice1EndpointImpl implements Microservice1Endpoint {

    private final EurekaClient eurekaClient;

    @Value("${spring.application.name}")
    private String appName;

    @Override
    public String getMicroserviceName() {
        log.info("Receive a request!");
        return String.format(
                "Name: '%s' Time: '%s'",
                eurekaClient.getApplication(appName).getName(),
                Instant.now()
        );
    }
}
