package com.showmeyourcode.spring_cloud.client.rest;

import com.showmeyourcode.spring_cloud.client.configuration.Microservice1ApiClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ClientController {

    private final Microservice1ApiClient microservice1;
    private final io.swagger.client.api.Microservice2ControllerImplApi microservice2;

    public ClientController(Microservice1ApiClient microservice1,
                            io.swagger.client.api.Microservice2ControllerImplApi microservice2) {
        this.microservice1 = microservice1;
        this.microservice2 = microservice2;
    }

    @RequestMapping("api/service1")
    public String getMicroservice1Name() {
        log.info("Calling service1...");
        return microservice1.getMicroserviceName();
    }

    @RequestMapping("api/service2/resttemplate")
    public String getMicroservice2NameRestTemplate() {
        log.info("Calling service2 (RestTemplate) ...");
        return microservice2.getMicroserviceNameUsingGET();
    }

//    @RequestMapping("api/service2/openfeign")
//    public String getMicroservice2NameOpenFeign() {
//        log.info("Calling service2 (OpenFeign) ...");
//        return microservice2OpenFeign.getMicroserviceNameUsingGET().getBody();
//    }
}
