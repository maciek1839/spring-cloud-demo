package com.showmeyourcode.spring_cloud.demo.shop.service;

import com.showmeyourcode.spring_cloud.demo.shop.configuration.ItemsWarehouseApiClient;
import com.showmeyourcode.spring_cloud.demo.shop.generated.model.ItemResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ItemsService {

    private final ItemsWarehouseApiClient itemsApi;

    public ItemsService(ItemsWarehouseApiClient itemsApi) {
        this.itemsApi = itemsApi;
    }

    public ResponseEntity<List<ItemResponse>> getAll(String xClientId) {
        log.info("Calling GET ALL ...");
        return itemsApi.getAll(xClientId);
    }

    public ResponseEntity<ItemResponse> getById(String xClientId, String id) {
        log.info("Calling GET BY ID ...");
        return itemsApi.getById(xClientId, id);
    }
}
