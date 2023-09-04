package com.showmeyourcode.spring_cloud.demo.factory.service;

import com.showmeyourcode.spring_cloud.demo.factory.model.NewOrderRequest;
import com.showmeyourcode.spring_cloud.demo.factory.model.NewItem;
import com.showmeyourcode.spring_cloud.demo.factory.model.Order;
import com.showmeyourcode.spring_cloud.demo.factory.model.OrderStatus;
import com.showmeyourcode.spring_cloud.demo.factory.model.OrdersReportResponse;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrdersService {

    private final IdProvider idProvider;
    private final ApplicationEventPublisher applicationEventPublisher;
    private final List<Order> orders = Collections.synchronizedList(new ArrayList<>());
    @Value("${orders.processing-time}")
    private String processingTimeInSec;

    public Mono<Void> cancel(UUID id) {
        var order = orders.stream().filter(o -> o.getId().equals(id)).findAny();
        if (order.isPresent()) {
            if (order.get().getStatus() == OrderStatus.CREATED) {
                orders.remove(order.get());
                applicationEventPublisher.publishEvent(new NewOrderApplicationEvent(order.get()));
                return Mono.empty();
            } else {
                return Mono.error(new OrderCancellationException("Cannot cancel an order with ID: " + id));
            }
        } else {
            return Mono.error(new OrderNotFoundException("Cannot find an order with ID: " + id));
        }
    }

    public Mono<UUID> create(NewOrderRequest newOrder) {
        var id = idProvider.generateId();
        orders.add(
                new Order(id, new NewItem(newOrder.getItem().getName()), newOrder.getCustomerName(), newOrder.getComment(), OrderStatus.CREATED)
        );
        return Mono.just(id);
    }

    public Mono<OrdersReportResponse> createReport(String clientId) {
        return Mono.just(new OrdersReportResponse("report", clientId));
    }

    public Mono<Void> deleteAll(){
        orders.clear();
        return Mono.empty();
    }

    @PostConstruct
    private void init() {
        Flux.interval(Duration.ofSeconds(Integer.parseInt(processingTimeInSec)))
                .onBackpressureDrop()
                .doOnEach(e -> {
                    log.info("Processing orders...");
                    processOrders();
                })
                .subscribeOn(Schedulers.boundedElastic())
                .subscribe();
    }

    private void processOrders() {
        var ordersToProcess = orders.stream().filter(o -> o.getStatus() == OrderStatus.CREATED).toList();
        for (Order order : ordersToProcess) {
            if (order.getStatus() == OrderStatus.CREATED) {
                order.setStatus(OrderStatus.DONE);
                applicationEventPublisher.publishEvent(new NewOrderApplicationEvent(order));
            }
        }
    }
}
