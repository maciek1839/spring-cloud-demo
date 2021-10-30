package com.showmeyourcode.spring_cloud.standalone_service.api;

import com.showmeyourcode.spring_cloud.standalone_service.model.StandaloneModel1;
import com.showmeyourcode.spring_cloud.standalone_service.model.StandaloneModel3;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.List;

@RestController
public class StandaloneControllerImpl implements StandaloneController {

    @Value("${spring.application.name}")
    private String appName;

    @Override
    public String getMicroserviceName() {
        return String.format(
                "Name standalone service: '%s' Time: '%s'",
                appName,
                Instant.now());
    }

    @Override
    public List<StandaloneModel1> getProperties() {
        return null;
    }

    @Override
    public String addProperty(@RequestBody StandaloneModel3 newProperty) {
        return null;
    }

    @Override
    public String deleteProperty(String id) {
        return null;
    }
}
