package com.showmeyourcode.spring_cloud.demo.warehouse.service;

import com.showmeyourcode.spring_cloud.demo.warehouse.generated.ItemsApi;
import com.showmeyourcode.spring_cloud.demo.warehouse.generated.model.ItemResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ItemsService {

    private final ItemsApi itemsApi;

    public ItemsService(ItemsApi itemsApi) {
        this.itemsApi = itemsApi;
    }

    public ResponseEntity<List<ItemResponse>> getAll(String xClientId) {
        log.info("Calling GET ALL ...");
        return itemsApi.getAllWithHttpInfo(xClientId);
    }

    public ResponseEntity<ItemResponse> getById(String xClientId, String id) {
        log.info("Calling GET BY ID ...");
        return itemsApi.getByIdWithHttpInfo(xClientId, id);
    }
}
