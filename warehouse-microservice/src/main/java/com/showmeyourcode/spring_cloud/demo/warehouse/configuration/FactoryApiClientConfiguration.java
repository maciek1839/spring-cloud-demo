package com.showmeyourcode.spring_cloud.demo.warehouse.configuration;


import com.showmeyourcode.spring_cloud.demo.warehouse.ApiClient;
import com.showmeyourcode.spring_cloud.demo.warehouse.generated.ItemsApi;
import com.showmeyourcode.spring_cloud.demo.warehouse.generated.OrdersApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * A manual API client configuration with a fixed hostname.
 */
@Configuration
public class FactoryApiClientConfiguration {

    @Value("${factoryMicroservice.basePath}")
    private String factoryMicroserviceHostname;

    @Bean
    public ItemsApi itemsApi() {
        ApiClient apiClient = new ApiClient();
        apiClient.setBasePath(factoryMicroserviceHostname);
        return new ItemsApi(apiClient);
    }

    @Bean
    public OrdersApi ordersApi() {
        ApiClient apiClient = new ApiClient();
        apiClient.setBasePath(factoryMicroserviceHostname);
        return new OrdersApi(apiClient);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }
}
