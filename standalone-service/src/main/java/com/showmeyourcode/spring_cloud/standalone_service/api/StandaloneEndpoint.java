package com.showmeyourcode.spring_cloud.standalone_service.api;

import com.showmeyourcode.spring_cloud.standalone_service.model.StandaloneModel1;
import com.showmeyourcode.spring_cloud.standalone_service.model.StandaloneModel3;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(description = "Set of endpoints for Creating, Retrieving, Updating and Deleting of StandaloneModels.", tags = {"Standalone"})
public interface StandaloneEndpoint {

    String ENDPOINT_PATH = "/api/standalone-microservice";
    String ENDPOINT_PROPS_PATH = "/api/standalone-microservice/properties";

    @GetMapping(ENDPOINT_PATH)
    String getMicroserviceName();

    @ApiOperation("Returns list of all Persons in the system.")
    @GetMapping(ENDPOINT_PROPS_PATH)
    List<StandaloneModel1> getProperties();

    @PostMapping(ENDPOINT_PROPS_PATH)
    String addProperty(@RequestBody StandaloneModel3 newProperty);

    @ApiResponses({
            @ApiResponse(code = 204, message = "Property has been deleted"),
            @ApiResponse(code = 404, message = "Property does not exist")
    })
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @DeleteMapping(ENDPOINT_PROPS_PATH+"/{id}")
    void deleteProperty(@PathVariable String id);
}
