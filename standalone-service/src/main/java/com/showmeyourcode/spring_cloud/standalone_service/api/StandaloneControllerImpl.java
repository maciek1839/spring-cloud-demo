package com.showmeyourcode.spring_cloud.standalone_service.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
public class StandaloneControllerImpl implements StandaloneController {


    @Value("${spring.application.name}")
    private String appName;

    @Override
    public String getMicroserviceName() {
        return String.format(
                "Name standalone service: '%s' Time: '%s'",
                appName,
                Instant.now());
    }
}
