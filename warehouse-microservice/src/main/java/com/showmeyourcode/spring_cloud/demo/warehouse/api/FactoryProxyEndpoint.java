package com.showmeyourcode.spring_cloud.demo.warehouse.api;

import com.showmeyourcode.spring_cloud.demo.warehouse.generated.model.ItemResponse;
import com.showmeyourcode.spring_cloud.demo.warehouse.generated.model.NewOrderRequest;
import com.showmeyourcode.spring_cloud.demo.warehouse.generated.model.OrdersReportResponse;
import com.showmeyourcode.spring_cloud.demo.warehouse.service.ItemsService;
import com.showmeyourcode.spring_cloud.demo.warehouse.service.OrdersService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FactoryProxyEndpoint implements FactoryProxyEndpointSpecification {

    private final OrdersService ordersService;
    private final ItemsService itemsService;

    public FactoryProxyEndpoint(OrdersService ordersService, ItemsService itemsService) {
        this.ordersService = ordersService;
        this.itemsService = itemsService;
    }

    @Override
    public ResponseEntity<Void> cancel(String xClientId, String id) {
        return ordersService.cancel(xClientId, id);
    }

    @Override
    public ResponseEntity<Void> create(String xClientId, NewOrderRequest body) {
        return ordersService.create(xClientId, body);
    }

    @Override
    public ResponseEntity<List<ItemResponse>> getAll(String xClientId) {
        return itemsService.getAll(xClientId);
    }

    @Override
    public ResponseEntity<ItemResponse> getById(String xClientId, String id) {
        return itemsService.getById(xClientId, id);
    }

    @Override
    public ResponseEntity<OrdersReportResponse> getReport(String xClientId, String xClientIp) {
        return ordersService.getReport(xClientId, xClientIp);
    }
}
