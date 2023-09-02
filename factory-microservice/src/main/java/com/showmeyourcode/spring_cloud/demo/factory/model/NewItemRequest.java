package com.showmeyourcode.spring_cloud.demo.factory.model;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewItemRequest {
    @Schema(required = true)
    private String name;
}
