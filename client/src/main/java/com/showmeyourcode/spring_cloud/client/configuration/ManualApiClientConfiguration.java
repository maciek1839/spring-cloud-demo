package com.showmeyourcode.spring_cloud.client.configuration;

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

    @Value("${standaloneService.basePath}")
    private String standaloneServicePath;
    @Value("${client2EurekaName.name}")
    private String client2ServiceEurekaName;

    /**
     * An example of configuration a client with a fixed URL e.g. loadbalancer.
     */
    @Bean
    public io.swagger.client.api.StandaloneControllerImplApi standaloneService() {
        io.swagger.client.ApiClient apiClient = new io.swagger.client.ApiClient();
        apiClient.setBasePath(standaloneServicePath);
        return new io.swagger.client.api.StandaloneControllerImplApi(apiClient);
    }

    /**
     * An example of using Ribbon/LoadBalancer with RestTemplate.
     * The URL should contain the service name registered in Eureka which will be changed by
     * Ribbon for a particular IP of a service instance (provided from Eureka).
     */
    @LoadBalanced
    @Bean
    @Qualifier("service2Client")
    public RestTemplate getService2ClientRestTemplate() {
        return new RestTemplateBuilder()
                .rootUri("http://" + client2ServiceEurekaName)
                .setConnectTimeout(Duration.ofMillis(300000))
                .setReadTimeout(Duration.ofMillis(300000))
                .build();
    }
}
