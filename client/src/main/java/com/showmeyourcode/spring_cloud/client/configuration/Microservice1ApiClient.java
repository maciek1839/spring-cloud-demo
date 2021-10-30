package com.showmeyourcode.spring_cloud.client.configuration;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * An example of manual configuration of a service.
 */
@FeignClient("spring-cloud-eureka-service1")
public interface Microservice1ApiClient {

    @RequestMapping("api/microservice1")
    String getMicroserviceName();
}
