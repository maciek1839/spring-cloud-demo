package com.showmeyourcode.spring_cloud.client.api;

import com.showmeyourcode.spring_cloud.client.configuration.ApiPathConstant;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RestController
@Tag(name = "Service2 Proxy", description = "Service2 Proxy endpoint")
@RequestMapping(ApiPathConstant.SERVICE_2)
public class Service2ProxyEndpoint {

    public static final String PATH_OPENFEIGN = "/openfeign";
    public static final String PATH_RESTTEMPLATE = "/resttemplate";
    public static final String SERVICE2_INTERNAL_PATH = "/microservice2/api/v1";

    private final RestTemplate restTemplateClient2;
    private final io.swagger.api.Microservice2ApiClient client2OpenFeign;

    public Service2ProxyEndpoint(@Qualifier("service2Client") RestTemplate restTemplate,
                                 io.swagger.api.Microservice2ApiClient client2OpenFeign) {
        this.restTemplateClient2 = restTemplate;
        this.client2OpenFeign = client2OpenFeign;
    }

    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Get the microservice 2 name",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    @GetMapping(PATH_OPENFEIGN)
    public String getMicroservice2OpenFeign() {
        log.info("Calling service2 (OpenFeign) ...");
        return client2OpenFeign.getMicroserviceName().getBody();
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
