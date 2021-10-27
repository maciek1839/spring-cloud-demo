package com.showmeyourcode.spring_cloud.microservice1.api;

import com.netflix.discovery.EurekaClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@Slf4j
@RestController
public class Microservice1ControllerImpl implements Microservice1Controller {

    private final EurekaClient eurekaClient;

    @Value("${spring.application.name}")
    private String appName;

    public Microservice1ControllerImpl(EurekaClient eurekaClient) {
        this.eurekaClient = eurekaClient;
    }

    @Override
    public String getMicroserviceName() {
        log.info("Receive a request!");
        return String.format(
                "Name: '%s' Time: '%s'",
                eurekaClient.getApplication(appName).getName(),
                Instant.now());
    }
}
