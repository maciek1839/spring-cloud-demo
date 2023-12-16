package com.showmeyourcode.spring_cloud.demo.reporting.configuration;

import com.showmeyourcode.spring_cloud.demo.reporting.generated.shop.ApiApi;
import org.springframework.cloud.openfeign.FeignClient;

// Example of using Ribbon & Eureka to get a microservice address/host
@FeignClient(value = "${shopMicroservice.eurekaName}", name = "${shopMicroservice.eurekaName}", path = "/shop")
public interface ShopApiClient extends ApiApi {
}
