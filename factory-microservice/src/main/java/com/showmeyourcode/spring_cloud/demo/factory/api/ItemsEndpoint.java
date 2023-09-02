package com.showmeyourcode.spring_cloud.demo.factory.api;

import com.showmeyourcode.spring_cloud.demo.factory.constant.HttpHeaderConstant;
import com.showmeyourcode.spring_cloud.demo.factory.model.ItemResponse;
import com.showmeyourcode.spring_cloud.demo.factory.service.ItemsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
public class ItemsEndpoint implements ItemsEndpointSpecification {

    private final ItemsService itemsService;

    public ItemsEndpoint(ItemsService itemsService) {
        this.itemsService = itemsService;
    }

    @Override
    public Mono<List<ItemResponse>> getAll(
            @RequestHeader(name = HttpHeaderConstant.X_CLIENT_ID_HEADER) String clientId
    ) {
        log.info("Received a GET all items request from {}.", clientId);
        return itemsService.getAll();
    }

    @Override
    public Mono<ItemResponse> getById(
            @RequestHeader(name = HttpHeaderConstant.X_CLIENT_ID_HEADER) String clientId,
            UUID id) {
        log.info("Received a GET item ({}) request from {}.", id, clientId);
        return itemsService.getById(id);
    }
}
