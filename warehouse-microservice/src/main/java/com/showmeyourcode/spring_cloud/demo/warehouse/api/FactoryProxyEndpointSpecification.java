package com.showmeyourcode.spring_cloud.demo.warehouse.api;


import com.showmeyourcode.spring_cloud.demo.warehouse.generated.ApiApi;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.web.bind.annotation.RequestMapping;

@SecurityScheme(
        name = "basicAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "basic"
)
@RequestMapping(FactoryProxyEndpointSpecification.ENDPOINT_PATH)
public interface FactoryProxyEndpointSpecification extends ApiApi {

    String ENDPOINT_PATH = "";
    String ORDERS_API_PATH = "/api/v1/orders";
    String ITEMS_API_PATH = "/api/v1/items";
    String REPORT_PATH = "/report";
}
