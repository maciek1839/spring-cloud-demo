package com.showmeyourcode.spring_cloud.client.api;

import com.showmeyourcode.spring_cloud.client.configuration.ApiPathConstant;
import io.swagger.api.Microservice2ControllerImplApiClient;
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
@RequestMapping(ApiPathConstant.SERVICE_2)
public class Service2ProxyEndpoint {

    private final RestTemplate restTemplateClient2;
    private final io.swagger.api.Microservice2ControllerImplApiClient client2OpenFeign;

    public Service2ProxyEndpoint(@Qualifier("service2Client") RestTemplate restTemplate,
                                 Microservice2ControllerImplApiClient client2OpenFeign) {
        this.restTemplateClient2 = restTemplate;
        this.client2OpenFeign = client2OpenFeign;
    }

    @RequestMapping("/openfeign")
    public String getMicroservice2OpenFeign() {
        log.info("Calling service2 (OpenFeign) ...");
        return client2OpenFeign.getMicroserviceNameUsingGET().getBody();
    }

    @RequestMapping("/resttemplate")
    public String getMicroservice2NameRestTemplate() {
        log.info("Calling service2 (RestTemplate) ...");
        HttpEntity<String> httpEntity = new HttpEntity<>(new HttpHeaders());
        return restTemplateClient2.exchange("/api/microservice2",
                HttpMethod.GET,
                httpEntity,
                String.class
        ).getBody();
    }
}
