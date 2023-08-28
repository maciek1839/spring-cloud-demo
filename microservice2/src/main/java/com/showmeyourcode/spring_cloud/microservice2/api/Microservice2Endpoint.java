package com.showmeyourcode.spring_cloud.microservice2.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Tag(name = "Microservice2", description = "Microservice2 endpoint")
public interface Microservice2Endpoint {

    String ENDPOINT_PATH = "/api/v1";

    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Get the microservice name",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    @GetMapping(ENDPOINT_PATH)
    String getMicroserviceName();
}
