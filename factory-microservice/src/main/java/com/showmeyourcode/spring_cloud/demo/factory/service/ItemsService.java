package com.showmeyourcode.spring_cloud.demo.factory.service;

import com.showmeyourcode.spring_cloud.demo.factory.model.NewItem;
import com.showmeyourcode.spring_cloud.demo.factory.model.Order;
import com.showmeyourcode.spring_cloud.demo.factory.model.Item;
import com.showmeyourcode.spring_cloud.demo.factory.model.ItemResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class ItemsService implements ApplicationListener<NewOrderApplicationEvent> {

    private final IdProvider idProvider;
    private final List<Item> items = Collections.synchronizedList(new ArrayList<>());

    public ItemsService(IdProvider idProvider) {
        this.idProvider = idProvider;
    }

    public Mono<List<ItemResponse>> getAll() {
        return Mono.just(items.stream().map(p -> new ItemResponse(p.id(), p.name())).toList());
    }

    public Mono<ItemResponse> getById(UUID id) {
        return items.stream()
                .filter(p -> p.id().equals(id))
                .findAny()
                .map(p -> Mono.just(new ItemResponse(p.id(), p.name())))
                .orElse(Mono.error(new ItemNotFoundException("Item id not found. ID: " + id)));
    }

    @Override
    public void onApplicationEvent(NewOrderApplicationEvent newOrderApplicationEvent) {
        log.info("Producing a new item for order: {}", newOrderApplicationEvent.getSource());
        var order = (Order) newOrderApplicationEvent.getSource();
        items.add(new Item(idProvider.generateId(), order.getNewItem().name()));
    }

    public Mono<Void> deleteAll(){
        items.clear();
        return Mono.empty();
    }

    public Mono<UUID> addItem(NewItem newItem){
        var id = idProvider.generateId();
        items.add(new Item(id, newItem.name()));
        return Mono.just(id);
    }
}
