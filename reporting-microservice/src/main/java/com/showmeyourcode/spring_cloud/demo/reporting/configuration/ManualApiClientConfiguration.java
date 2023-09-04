package com.showmeyourcode.spring_cloud.demo.reporting.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class ManualApiClientConfiguration {

    public static final String SHOP_REST_TEMPLATE = "shopMicroserviceRestTemplate";
    public static final String FACTORY_REST_TEMPLATE = "factoryMicroserviceRestTemplate";
    public static final String WAREHOUSE_REST_TEMPLATE = "warehouseMicroserviceRestTemplate";

    @Value("${warehouseMicroservice.eurekaName}")
    private String warehouseMicroserviceEurekaName;
    @Value("${shopMicroservice.eurekaName}")
    private String shopMicroserviceEurekaName;
    @Value("${factoryMicroservice.url}")
    private String factoryMicroserviceUrl;

    private final int DEFAULT_TIMEOUT = 300000;

    /**
     * An example of using Ribbon/LoadBalancer with RestTemplate.
     * The URL should contain the service name registered in Eureka which will be changed by
     * Ribbon for a particular IP of a service instance (provided from Eureka).
     */
    @LoadBalanced
    @Bean(WAREHOUSE_REST_TEMPLATE)
    public RestTemplate getWarehouseMicroserviceRestTemplate() {
        return new RestTemplateBuilder()
                .rootUri("http://" + warehouseMicroserviceEurekaName)
                .setConnectTimeout(Duration.ofMillis(DEFAULT_TIMEOUT))
                .setReadTimeout(Duration.ofMillis(DEFAULT_TIMEOUT))
                .build();
    }

    @LoadBalanced
    @Bean(SHOP_REST_TEMPLATE)
    public RestTemplate getShopMicroserviceRestTemplate() {
        return new RestTemplateBuilder()
                .rootUri("http://" + shopMicroserviceEurekaName)
                .setConnectTimeout(Duration.ofMillis(DEFAULT_TIMEOUT))
                .setReadTimeout(Duration.ofMillis(DEFAULT_TIMEOUT))
                .build();
    }

    @Bean(FACTORY_REST_TEMPLATE)
    public RestTemplate getFactoryMicroserviceRestTemplate() {
        return new RestTemplateBuilder()
                .rootUri(factoryMicroserviceUrl)
                .setConnectTimeout(Duration.ofMillis(DEFAULT_TIMEOUT))
                .setReadTimeout(Duration.ofMillis(DEFAULT_TIMEOUT))
                .build();
    }
}
