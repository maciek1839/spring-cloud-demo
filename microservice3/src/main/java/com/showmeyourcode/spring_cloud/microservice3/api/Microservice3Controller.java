package com.showmeyourcode.spring_cloud.microservice3.api;

import org.springframework.web.bind.annotation.RequestMapping;

public interface Microservice3Controller {

    @RequestMapping("api/microservice3")
    String getMicroserviceName();
}
