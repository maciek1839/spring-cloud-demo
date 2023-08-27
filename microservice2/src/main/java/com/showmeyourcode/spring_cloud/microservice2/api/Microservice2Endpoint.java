package com.showmeyourcode.spring_cloud.microservice2.api;

import org.springframework.web.bind.annotation.RequestMapping;

public interface Microservice2Endpoint {

    String ENDPOINT_PATH = "/api/v1";

    @RequestMapping(ENDPOINT_PATH)
    String getMicroserviceName();
}
