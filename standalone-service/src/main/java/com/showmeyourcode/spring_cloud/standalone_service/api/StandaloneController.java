package com.showmeyourcode.spring_cloud.standalone_service.api;

import com.showmeyourcode.spring_cloud.standalone_service.model.StandaloneModel1;
import com.showmeyourcode.spring_cloud.standalone_service.model.StandaloneModel3;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(description = "Set of endpoints for Creating, Retrieving, Updating and Deleting of StandaloneModels.", tags = {"Standalone"})
public interface StandaloneController {

    @GetMapping("api/microservice2")
    String getMicroserviceName();

    @ApiOperation("Returns list of all Persons in the system.")
    @GetMapping("api/microservice2/properties")
    List<StandaloneModel1> getProperties();

    @PostMapping("api/microservice2/properties")
    String addProperty(@RequestBody StandaloneModel3 newProperty);

    @DeleteMapping("api/microservice2/properties/{id}")
    String deleteProperty(@PathVariable String id);
}
