package com.showmeyourcode.spring_cloud.client.api;

import com.showmeyourcode.spring_cloud.client.configuration.ApiPathConstant;
import com.showmeyourcode.spring_cloud.client.configuration.Microservice1ApiClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(ApiPathConstant.SERVICE_1)
public class Service1ProxyEndpoint {

    public static final String PATH_MANUAL_OPENFEIGN = "/manual/openfeign";

    private final Microservice1ApiClient microservice1;

    @RequestMapping(PATH_MANUAL_OPENFEIGN)
    public String getMicroservice1Name() {
        log.info("Calling service1 (Manual Open Feign configuration)...");
        return microservice1.getMicroserviceName();
    }
}
