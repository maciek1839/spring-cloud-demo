package com.showmeyourcode.spring_cloud.client.api;

import com.showmeyourcode.spring_cloud.client.configuration.ApiPathConstant;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
@Tag(name = "Standalone Service Proxy", description = "Standalone Service Proxy endpoint")
@RequestMapping(ApiPathConstant.STANDALONE_MANUAL)
public class StandaloneServiceProxyEndpoint {

    public static final String RESTTEMPLATE_ENDPOINT = "/resttemplate";

    private final io.swagger.client.api.StandaloneApi standaloneApi;

    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Get the standalone service name",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    @GetMapping(RESTTEMPLATE_ENDPOINT)
    public String getStandaloneServiceName() {
        log.info("Calling the standalone service (RestTemplate) ...");
        return standaloneApi.getMicroserviceName(null, null);
    }
}
