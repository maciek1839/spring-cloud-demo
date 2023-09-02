package com.showmeyourcode.spring_cloud.demo.client.api;

import com.showmeyourcode.spring_cloud.demo.client.constant.EndpointConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@Tag(name = "Warehouse Proxy", description = "Warehouse Proxy endpoint")
@RequestMapping(EndpointConstant.WAREHOUSE_MICROSERVICE)
public class WarehouseProxyEndpoint {

    public static final String INFO_PATH = "/info";

    private final RestTemplate restTemplateClient2;

    public WarehouseProxyEndpoint(RestTemplate restTemplate) {
        this.restTemplateClient2 = restTemplate;
    }

    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Get the Warehouse microservice info",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    @GetMapping(INFO_PATH)
    public String getMicroservice2NameRestTemplate() {
        log.info("Calling the Warehouse microservice (RestTemplate) ...");
        var headers = new LinkedMultiValueMap<>(Map.of("X-Client-Id", List.of("client-microservice")));
        HttpEntity<String> httpEntity = new HttpEntity<>(new HttpHeaders(headers));

        return restTemplateClient2.exchange("/warehouse/actuator/info",
                HttpMethod.GET,
                httpEntity,
                String.class
        ).getBody();
    }
}
