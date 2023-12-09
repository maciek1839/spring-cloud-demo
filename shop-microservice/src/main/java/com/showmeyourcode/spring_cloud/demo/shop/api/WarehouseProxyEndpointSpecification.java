package com.showmeyourcode.spring_cloud.demo.shop.api;

import com.showmeyourcode.spring_cloud.demo.shop.generated.ApiApi;
import com.showmeyourcode.spring_cloud.demo.shop.generated.model.ItemResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@SecurityScheme(
        name = "basicAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "basic"
)
@RequestMapping(WarehouseProxyEndpointSpecification.ENDPOINT_PATH)
public interface WarehouseProxyEndpointSpecification extends ApiApi {

    String ENDPOINT_PATH = "";
    String ORDERS_API_PATH = "/api/v1/orders";
    String ITEMS_API_PATH = "/api/v1/items";
    String REPORT_PATH = "/report";
    String BARGAINS_WAREHOUSE_PATH = "/api/v1/bargains";

    @Operation(summary = "Get all bargains (gRPC)", description = "", tags = {"Bargains"})
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully retrieved a number of bargains.",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ItemResponse.class))
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal Server Error. Please try again later.",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ItemResponse.class)))
            )
    })
    @GetMapping(
            value = BARGAINS_WAREHOUSE_PATH,
            produces = {"application/json"}
    )
    ResponseEntity<Integer> getAllBargains(
            @Parameter(
                    in = ParameterIn.HEADER,
                    description = "A client's ID (a service name)",
                    required = true,
                    schema = @Schema()
            )
            @RequestHeader(value = "X-Client-Id", required = true) String xClientId
    );
}
