package com.showmeyourcode.spring_cloud.demo.reporting.configuration;

import com.showmeyourcode.spring_cloud.demo.reporting.generated.warehouse.ItemsApi;
import com.showmeyourcode.spring_cloud.demo.reporting.generated.warehouse.OrdersApi;
import org.springframework.cloud.openfeign.FeignClient;

// Example of a static URL to configure a Feign client.
@FeignClient(name = "${warehouseMicroservice.eurekaName}", value = "${warehouseMicroservice.eurekaName}", path = "/warehouse")
public interface WarehouseApiClient extends WarehouseItemsApiClient {
}

interface WarehouseItemsApiClient extends ItemsApi {
}

interface WarehouseOrdersApiClient extends OrdersApi {
}
