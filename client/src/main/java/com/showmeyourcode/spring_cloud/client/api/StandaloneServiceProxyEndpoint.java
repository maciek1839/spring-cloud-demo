package com.showmeyourcode.spring_cloud.client.api;

import com.showmeyourcode.spring_cloud.client.configuration.ApiPathConstant;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(ApiPathConstant.STANDALONE_MANUAL)
public class StandaloneServiceProxyEndpoint {

    public static final String RESTTEMPLATE_ENDPOINT = "/resttemplate";

    private final io.swagger.client.api.StandaloneApi standaloneApi;

    @RequestMapping(RESTTEMPLATE_ENDPOINT)
    public String getStandaloneServiceName() {
        log.info("Calling the standalone service (RestTemplate) ...");
        return standaloneApi.getMicroserviceNameUsingGET();
    }
}
