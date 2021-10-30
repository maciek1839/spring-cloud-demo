package com.showmeyourcode.spring_cloud.standalone_service.api;

import org.springframework.web.bind.annotation.RequestMapping;

public interface StandaloneController {

    @RequestMapping("api/microservice2")
    String getMicroserviceName();
}
