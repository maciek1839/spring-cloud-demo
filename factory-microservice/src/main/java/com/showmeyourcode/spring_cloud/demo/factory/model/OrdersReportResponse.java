package com.showmeyourcode.spring_cloud.demo.factory.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrdersReportResponse {
    @Schema(required = true)
    private String name;
    @Schema(required = true)
    private String requestedBy;
}
