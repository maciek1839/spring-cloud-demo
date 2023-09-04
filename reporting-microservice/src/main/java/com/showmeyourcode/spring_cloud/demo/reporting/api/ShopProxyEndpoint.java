package com.showmeyourcode.spring_cloud.demo.reporting.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.showmeyourcode.spring_cloud.demo.reporting.constant.EndpointConstant;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

import static com.showmeyourcode.spring_cloud.demo.reporting.configuration.ManualApiClientConfiguration.SHOP_REST_TEMPLATE;


@Slf4j
@RestController
@Tag(name = "Shop Proxy", description = "Shop Proxy endpoint")
@RequestMapping(EndpointConstant.SHOP_MICROSERVICE)
public class ShopProxyEndpoint {

    public static final String INFO_PATH = "/info";

    private final ObjectMapper objectMapper;
    private final RestTemplate restTemplate;


    public ShopProxyEndpoint(ObjectMapper objectMapper,
                             @Qualifier(SHOP_REST_TEMPLATE)
                             RestTemplate restTemplate) {
        this.objectMapper = objectMapper;
        this.restTemplate = restTemplate;
    }

    @SneakyThrows
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Get the Shop microservice info",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    @GetMapping(value = INFO_PATH,produces = MediaType.APPLICATION_JSON_VALUE)
    public JsonNode getWarehouseActuatorInfo() {
        log.info("Calling the Shop microservice (RestTemplate) ...");
        var headers = new LinkedMultiValueMap<>(Map.of("X-Client-Id", List.of("reporting-microservice")));
        HttpEntity<String> httpEntity = new HttpEntity<>(new HttpHeaders(headers));

        return objectMapper.readValue(restTemplate.exchange("/shop/actuator/info",
                HttpMethod.GET,
                httpEntity,
                String.class
        ).getBody(), JsonNode.class);
    }
}
