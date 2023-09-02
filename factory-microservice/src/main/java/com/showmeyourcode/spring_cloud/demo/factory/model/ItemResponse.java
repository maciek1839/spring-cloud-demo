package com.showmeyourcode.spring_cloud.demo.factory.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class ItemResponse {
    @Schema(required = true)
    private UUID id;
    @Schema(required = true)
    private String name;
}
