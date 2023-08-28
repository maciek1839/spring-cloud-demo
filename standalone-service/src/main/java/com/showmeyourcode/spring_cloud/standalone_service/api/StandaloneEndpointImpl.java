package com.showmeyourcode.spring_cloud.standalone_service.api;

import com.showmeyourcode.spring_cloud.standalone_service.constant.HttpHeaderConstant;
import com.showmeyourcode.spring_cloud.standalone_service.model.StandaloneModel1;
import com.showmeyourcode.spring_cloud.standalone_service.model.StandaloneModel3;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.Collections;
import java.util.List;

@Slf4j
@RestController
public class StandaloneEndpointImpl implements StandaloneEndpoint {

    @Value("${spring.application.name}")
    private String appName;

    @Override
    public Mono<String> getMicroserviceName(
            @RequestHeader(value = HttpHeaderConstant.X_CLIENT_ID_HEADER, required = false) String clientId,
            @RequestHeader(value = HttpHeaderConstant.X_CLIENT_IP_HEADER, required = false) String clientIp
    ) {
        log.info("Received a request from {} ({})", clientId, clientIp);
        return Mono.just(String.format(
                "Name: '%s' Time: '%s'",
                appName,
                Instant.now()));
    }

    @Override
    public Mono<List<StandaloneModel1>> getProperties() {
        return Mono.just(Collections.emptyList());
    }

    @Override
    public Mono<String> addProperty(@RequestBody StandaloneModel3 newProperty) {
        return Mono.just("730aad54-4f02-4872-99b0-e3bfda9c4332");
    }

    @Override
    public Mono<Void> deleteProperty(String id) {
        log.warn("Property '{}' deleted.", id);
        return Mono.empty();
    }
}
