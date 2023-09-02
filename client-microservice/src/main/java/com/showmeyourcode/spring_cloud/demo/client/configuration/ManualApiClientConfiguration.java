package com.showmeyourcode.spring_cloud.demo.client.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class ManualApiClientConfiguration {

    @Value("${warehouseEurekaName.name}")
    private String warehouseMicroserviceEurekaName;

    /**
     * An example of using Ribbon/LoadBalancer with RestTemplate.
     * The URL should contain the service name registered in Eureka which will be changed by
     * Ribbon for a particular IP of a service instance (provided from Eureka).
     */
    @LoadBalanced
    @Bean
    public RestTemplate getService2ClientRestTemplate() {
        return new RestTemplateBuilder()
                .rootUri("http://" + warehouseMicroserviceEurekaName)
                .setConnectTimeout(Duration.ofMillis(300000))
                .setReadTimeout(Duration.ofMillis(300000))
                .build();
    }
}
