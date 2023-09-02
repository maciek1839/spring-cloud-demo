package com.showmeyourcode.spring_cloud.demo.factory.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class Order {

    private UUID id;
    private NewItem newItem;
    private String customerName;
    private String comment;
    private OrderStatus status;

}
