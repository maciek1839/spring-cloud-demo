package com.showmeyourcode.spring_cloud.microservice1.api;

import org.springframework.web.bind.annotation.RequestMapping;

public interface Microservice1Controller {

    @RequestMapping("api/microservice1")
    String getMicroserviceName();
}
