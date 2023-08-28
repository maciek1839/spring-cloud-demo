package com.showmeyourcode.spring_cloud.client.api;

import com.showmeyourcode.spring_cloud.client.configuration.ApiPathConstant;
import com.showmeyourcode.spring_cloud.client.configuration.Microservice1ApiClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@Tag(name = "Service1 Proxy", description = "Service1 Proxy endpoint")
@RequestMapping(ApiPathConstant.SERVICE_1)
public class Service1ProxyEndpoint {

    public static final String PATH_MANUAL_OPENFEIGN = "/manual/openfeign";

    private final Microservice1ApiClient microservice1;

    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Get the microservice 1 name",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    @GetMapping(PATH_MANUAL_OPENFEIGN)
    public String getMicroservice1Name() {
        log.info("Calling service1 (Manual Open Feign configuration)...");
        return microservice1.getMicroserviceName();
    }
}
