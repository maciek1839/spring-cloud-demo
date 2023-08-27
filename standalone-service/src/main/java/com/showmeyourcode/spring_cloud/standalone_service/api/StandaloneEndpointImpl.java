package com.showmeyourcode.spring_cloud.standalone_service.api;

import com.showmeyourcode.spring_cloud.standalone_service.model.StandaloneModel1;
import com.showmeyourcode.spring_cloud.standalone_service.model.StandaloneModel2;
import com.showmeyourcode.spring_cloud.standalone_service.model.StandaloneModel3;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Max;
import java.time.Instant;
import java.util.Collections;
import java.util.List;

@Slf4j
@RestController
public class StandaloneEndpointImpl implements StandaloneEndpoint {

    @Value("${spring.application.name}")
    private String appName;

    @Override
    public String getMicroserviceName() {
        return String.format(
                "Name: '%s' Time: '%s'",
                appName,
                Instant.now());
    }

    @Override
    public List<StandaloneModel1> getProperties() {
        return Collections.emptyList();
    }

    @Override
    public String addProperty(@RequestBody StandaloneModel3 newProperty) {
        return "730aad54-4f02-4872-99b0-e3bfda9c4332";
    }

    @Override
    public void deleteProperty(String id) {
        log.warn("Property '{}' deleted.", id);
    }
}
