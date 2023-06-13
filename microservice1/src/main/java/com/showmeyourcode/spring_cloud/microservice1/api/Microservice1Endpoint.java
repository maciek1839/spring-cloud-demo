package com.showmeyourcode.spring_cloud.microservice1.api;

import org.springframework.web.bind.annotation.RequestMapping;

public interface Microservice1Endpoint {

    String ENDPOINT_PATH = "/api/microservice1";

    @RequestMapping(ENDPOINT_PATH)
    String getMicroserviceName();
}
