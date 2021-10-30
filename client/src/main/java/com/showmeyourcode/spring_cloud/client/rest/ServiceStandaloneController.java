package com.showmeyourcode.spring_cloud.client.rest;

import com.showmeyourcode.spring_cloud.client.configuration.ApiPathConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(ApiPathConstant.SERVICE_MANUAL)
public class ServiceStandaloneController {

    private final io.swagger.client.api.StandaloneApi standaloneApi;

    public ServiceStandaloneController(io.swagger.client.api.StandaloneApi standaloneApi) {
        this.standaloneApi = standaloneApi;
    }

    @RequestMapping("/resttemplate")
    public String getStandaloneServiceName() {
        log.info("Calling service3 (RestTemplate) ...");
        return standaloneApi.getMicroserviceNameUsingGET();
    }
}
