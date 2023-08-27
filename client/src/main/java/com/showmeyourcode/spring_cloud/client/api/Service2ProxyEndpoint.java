package com.showmeyourcode.spring_cloud.client.api;

import com.showmeyourcode.spring_cloud.client.configuration.ApiPathConstant;

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

    public static final String PATH_OPENFEIGN = "/openfeign";
    public static final String PATH_RESTTEMPLATE = "/resttemplate";
    public static final String SERVICE2_INTERNAL_PATH = "/microservice2/api/v1";

    private final RestTemplate restTemplateClient2;
    private final io.swagger.api.Microservice2EndpointImplApiClient client2OpenFeign;

    public Service2ProxyEndpoint(@Qualifier("service2Client") RestTemplate restTemplate,
                                 io.swagger.api.Microservice2EndpointImplApiClient client2OpenFeign) {
        this.restTemplateClient2 = restTemplate;
        this.client2OpenFeign = client2OpenFeign;
    }

    @RequestMapping(PATH_OPENFEIGN)
    public String getMicroservice2OpenFeign() {
        log.info("Calling service2 (OpenFeign) ...");
        return client2OpenFeign.getMicroserviceName4().getBody();
    }

    @RequestMapping(PATH_RESTTEMPLATE)
    public String getMicroservice2NameRestTemplate() {
        log.info("Calling service2 (RestTemplate) ...");
        HttpEntity<String> httpEntity = new HttpEntity<>(new HttpHeaders());
        return restTemplateClient2.exchange(SERVICE2_INTERNAL_PATH,
                HttpMethod.GET,
                httpEntity,
                String.class
        ).getBody();
    }
}
