package com.showmeyourcode.spring_cloud.standalone_service.api;

import com.showmeyourcode.spring_cloud.standalone_service.constant.HttpHeaderConstant;
import com.showmeyourcode.spring_cloud.standalone_service.model.StandaloneModel1;
import com.showmeyourcode.spring_cloud.standalone_service.model.StandaloneModel3;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@OpenAPIDefinition(
        info = @Info(
                title = "Standalone REST API",
                version = "0.0.1",
                description = "Standalone API specification."
        )
)
@Tag(name = "Standalone", description = "Standalone endpoint")
public interface StandaloneEndpoint {

    String ENDPOINT_PATH = "/api/v1";
    String ENDPOINT_PROPS_PATH = ENDPOINT_PATH+"/properties";

    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Get the microservice name",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    @GetMapping(ENDPOINT_PATH)
    Mono<String> getMicroserviceName(
            @RequestHeader(value = HttpHeaderConstant.X_CLIENT_ID_HEADER, required = false) String clientId,
            @RequestHeader(value = HttpHeaderConstant.X_CLIENT_IP_HEADER, required = false) String clientIp
    );

    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Returns list of properties.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    @GetMapping(ENDPOINT_PROPS_PATH)
    Mono<List<StandaloneModel1>> getProperties();

    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Create a property",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    @PostMapping(ENDPOINT_PROPS_PATH)
    Mono<String> addProperty(@RequestBody StandaloneModel3 newProperty);


    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(
            summary = "Create a property",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Property has been deleted"),
                    @ApiResponse(responseCode = "404", description = "Property does not exist")
            }
    )
    @DeleteMapping(ENDPOINT_PROPS_PATH+"/{id}")
    Mono<Void> deleteProperty(@PathVariable String id);
}
