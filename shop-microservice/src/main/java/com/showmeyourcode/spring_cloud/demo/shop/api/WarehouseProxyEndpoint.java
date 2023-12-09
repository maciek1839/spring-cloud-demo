package com.showmeyourcode.spring_cloud.demo.shop.api;

import com.showmeyourcode.spring_cloud.demo.shop.generated.model.ItemResponse;
import com.showmeyourcode.spring_cloud.demo.shop.generated.model.NewOrderRequest;
import com.showmeyourcode.spring_cloud.demo.shop.generated.model.OrdersReportResponse;
import com.showmeyourcode.spring_cloud.demo.shop.grpc.BargainProto;
import com.showmeyourcode.spring_cloud.demo.shop.service.BargainsService;
import com.showmeyourcode.spring_cloud.demo.shop.service.ItemsService;
import com.showmeyourcode.spring_cloud.demo.shop.service.OrdersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class WarehouseProxyEndpoint implements WarehouseProxyEndpointSpecification {

    private final OrdersService ordersService;
    private final ItemsService itemsService;
    private final BargainsService bargainsService;

    public WarehouseProxyEndpoint(
            OrdersService ordersService,
            ItemsService itemsService,
            BargainsService bargainsService) {
        this.ordersService = ordersService;
        this.itemsService = itemsService;
        this.bargainsService = bargainsService;
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
    public ResponseEntity<Integer> getAllBargains(String xClientId) {
        return bargainsService.getBargains();
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
