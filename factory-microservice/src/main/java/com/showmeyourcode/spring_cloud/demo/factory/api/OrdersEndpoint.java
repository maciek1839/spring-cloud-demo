package com.showmeyourcode.spring_cloud.demo.factory.api;

import com.showmeyourcode.spring_cloud.demo.factory.model.NewOrderRequest;
import com.showmeyourcode.spring_cloud.demo.factory.model.OrdersReportResponse;
import com.showmeyourcode.spring_cloud.demo.factory.service.OrdersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Slf4j
@RestController
public class OrdersEndpoint implements OrdersEndpointSpecification {

    private final OrdersService ordersService;
    @Value("${spring.application.name}")
    private String appName;
    @Value("${spring.webflux.base-path}")
    private String contextPath;

    public OrdersEndpoint(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    @Override
    public Mono<Void> cancel(String clientId, UUID id) {
        log.info("Received a CANCEL request from {} to cancel an order with ID: {}.",
                clientId, id);
        return ordersService.cancel(id);
    }

    @Override
    public Mono<ResponseEntity<Void>> create(String clientId, NewOrderRequest newOrder) {
        log.info("Received a CREATE request from {}.", clientId);
        UriComponentsBuilder.newInstance()
                .scheme("http").host("www.baeldung.com").path("/junit-5").build();
        return ordersService.create(newOrder)
                .map(orderId -> {
                    var uriLocation = contextPath + OrdersEndpoint.ENDPOINT_PATH + "/" + orderId;
                    var headers = new LinkedMultiValueMap<String, String>();
                    headers.add(HttpHeaders.LOCATION, uriLocation);
                    return new ResponseEntity(null, headers, HttpStatus.CREATED);
                });
    }

    @Override
    public Mono<OrdersReportResponse> getReport(String clientId, String clientIp) {
        log.info("Received a GET report request from {}/{}.",
                clientId, clientIp);
        return ordersService.createReport(clientId);
    }
}
