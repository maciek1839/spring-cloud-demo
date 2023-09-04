package com.showmeyourcode.spring_cloud.demo.warehouse.service;

import com.showmeyourcode.spring_cloud.demo.warehouse.generated.OrdersApi;
import com.showmeyourcode.spring_cloud.demo.warehouse.generated.model.NewOrderRequest;
import com.showmeyourcode.spring_cloud.demo.warehouse.generated.model.OrdersReportResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OrdersService {

    private final OrdersApi ordersApi;
    @Value("${server.servlet.context-path}")
    private String contextPath;

    public OrdersService(OrdersApi ordersApi) {
        this.ordersApi = ordersApi;
    }

    public ResponseEntity<Void> cancel(String xClientId, String id) {
        log.info("Calling CANCEL ...");
        return ordersApi.cancelWithHttpInfo(xClientId, id);
    }

    public ResponseEntity<Void> create(String xClientId, NewOrderRequest body) {
        log.info("Calling CREATE ...");
        var response = ordersApi.createWithHttpInfo(body, xClientId);

        var newLocation = response.getHeaders().getLocation().toString().replace("/factory", contextPath);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.LOCATION, newLocation);
        return ResponseEntity.status(response.getStatusCode()).headers(headers).build();
    }

    public ResponseEntity<OrdersReportResponse> getReport(String xClientId, String xClientIp) {
        log.info("Calling REPORT ...");
        return ordersApi.getReportWithHttpInfo(xClientId, xClientIp);
    }
}
