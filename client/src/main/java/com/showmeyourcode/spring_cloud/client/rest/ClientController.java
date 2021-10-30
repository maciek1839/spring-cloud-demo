package com.showmeyourcode.spring_cloud.client.rest;

import com.showmeyourcode.spring_cloud.client.configuration.Microservice1ApiClient;
import io.swagger.api.Microservice2ControllerImplApiClient;
import io.swagger.client.api.StandaloneControllerImplApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RestController
public class ClientController {

    private final RestTemplate restTemplateClient2;
    private final Microservice1ApiClient microservice1;
    private final io.swagger.client.api.StandaloneControllerImplApi standaloneService;
    private final io.swagger.api.Microservice2ControllerImplApiClient client2OpenFeign;

    public ClientController(Microservice1ApiClient microservice1,
                            StandaloneControllerImplApi standaloneService,
                            @Qualifier("service2Client") RestTemplate restTemplate, Microservice2ControllerImplApiClient client2OpenFeign) {
        this.microservice1 = microservice1;
        this.standaloneService = standaloneService;
        this.restTemplateClient2 = restTemplate;
        this.client2OpenFeign = client2OpenFeign;
    }

    @RequestMapping("api/service1/openfeign")
    public String getMicroservice1Name() {
        log.info("Calling service1...");
        return microservice1.getMicroserviceName();
    }

    @RequestMapping("api/service2/openfeign")
    public String getMicroservice2OpenFeign() {
        log.info("Calling service2 (OpenFeign) ...");
        return client2OpenFeign.getMicroserviceNameUsingGET().getBody();
    }

    @RequestMapping("api/service2/resttemplate")
    public String getMicroservice2NameRestTemplate() {
        log.info("Calling service2 (OpenFeign) ...");
        HttpEntity<String> httpEntity = new HttpEntity<>(new HttpHeaders());
        return restTemplateClient2.exchange("/api/microservice2", HttpMethod.GET, httpEntity, String.class).getBody();
    }

    @RequestMapping("api/service3/resttemplate")
    public String getStandaloneServiceName() {
        log.info("Calling service3 (RestTemplate) ...");
        return standaloneService.getMicroserviceNameUsingGET();
    }
}
