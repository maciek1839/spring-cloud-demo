package com.showmeyourcode.spring_cloud.client.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ManualApiClientConfiguration {

    /**
     * An example of configuration a client with a fixed URL e.g. loadbalancer.
     */
    @Bean
    public io.swagger.client.api.Microservice2ControllerImplApi microservice2ControllerImplApi(){
        io.swagger.client.ApiClient apiClient = new io.swagger.client.ApiClient();
        apiClient.setBasePath("http://localhost:8002");
        return new io.swagger.client.api.Microservice2ControllerImplApi(apiClient);
    }
}
