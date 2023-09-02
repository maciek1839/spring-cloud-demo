package com.showmeyourcode.spring_cloud.demo.shop.configuration;

import com.showmeyourcode.spring_cloud.demo.shop.generated.ItemsApi;
import org.springframework.cloud.openfeign.FeignClient;

// Example of using Ribbon & Eureka to get a microservice address/host
@FeignClient(value = "${warehouseMicroservice.name}", name = "${warehouseMicroservice.name}", path = "/warehouse")
public interface ItemsWarehouseApiClient extends ItemsApi {
}
