package com.showmeyourcode.spring_cloud.demo.factory.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NewOrderRequest {
    @Schema(required = true)
    private NewItemRequest item;
    @Schema(required = true)
    private String customerName;
    @Schema(required = false)
    private String comment;
}
