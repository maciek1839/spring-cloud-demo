package com.showmeyourcode.spring_cloud.microservice2.api;

import org.springframework.web.bind.annotation.RequestMapping;

public interface Microservice2Controller {

    @RequestMapping("api/microservice2")
    String getMicroserviceName();
}
