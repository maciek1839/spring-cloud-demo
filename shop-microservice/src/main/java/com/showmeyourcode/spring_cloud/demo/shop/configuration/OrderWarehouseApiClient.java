package com.showmeyourcode.spring_cloud.demo.shop.configuration;

import com.showmeyourcode.spring_cloud.demo.shop.generated.OrdersApi;
import org.springframework.cloud.openfeign.FeignClient;

// Example of a static URL to configure a Feign client.
@FeignClient(name = "${warehouseMicroservice.name}-orders", url = "${warehouseMicroservice.url}", path = "/warehouse")
public interface OrderWarehouseApiClient extends OrdersApi {
}
