package com.showmeyourcode.spring_cloud.demo.shop.service;

import com.showmeyourcode.spring_cloud.demo.shop.configuration.OrderWarehouseApiClient;
import com.showmeyourcode.spring_cloud.demo.shop.generated.model.NewOrderRequest;
import com.showmeyourcode.spring_cloud.demo.shop.generated.model.OrdersReportResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OrdersService {

    private final OrderWarehouseApiClient ordersApi;
    @Value("${server.servlet.context-path}")
    private String contextPath;

    public OrdersService(OrderWarehouseApiClient ordersApi) {
        this.ordersApi = ordersApi;
    }

    public ResponseEntity<Void> cancel(String xClientId, String id) {
        log.info("Calling CANCEL ...");
        return ordersApi.cancel(xClientId, id);
    }

    public ResponseEntity<Void> create(String xClientId, NewOrderRequest body) {
        log.info("Calling CREATE ...");
        var response = ordersApi.create(xClientId, body);

        var newLocation = response.getHeaders().getLocation().toString().replace("/warehouse", contextPath);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.LOCATION, newLocation);
        return ResponseEntity.status(response.getStatusCode()).headers(headers).build();
    }

    public ResponseEntity<OrdersReportResponse> getReport(String xClientId, String xClientIp) {
        log.info("Calling REPORT ...");
        return ordersApi.getReport(xClientId, xClientIp);
    }
}
