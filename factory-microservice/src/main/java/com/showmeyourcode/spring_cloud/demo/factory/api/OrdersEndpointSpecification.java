package com.showmeyourcode.spring_cloud.demo.factory.api;

import com.showmeyourcode.spring_cloud.demo.factory.constant.HttpHeaderConstant;
import com.showmeyourcode.spring_cloud.demo.factory.model.NewOrderRequest;
import com.showmeyourcode.spring_cloud.demo.factory.model.OrdersReportResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import reactor.core.publisher.Mono;

import java.util.UUID;


@SecurityScheme(
        name = "basicAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "basic"
)
@Tag(name = "Orders", description = "Orders endpoint")
@RequestMapping(OrdersEndpointSpecification.ENDPOINT_PATH)
public interface OrdersEndpointSpecification {

    String ENDPOINT_PATH = "/api/v1/orders";
    String REPORT_PATH = "/report";

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(
            summary = "Cancel an order",
            parameters = {
                    @Parameter(required = true,
                            name = HttpHeaderConstant.X_CLIENT_ID_HEADER,
                            description = "A client's ID (a service name)",
                            schema = @Schema(example = "warehouse-microservice"))
            },
            responses = {
                    @ApiResponse(responseCode = "204", description = "Cancellation succeed."),
                    @ApiResponse(responseCode = "410", description = "An order cannot be cancelled. It's already processed.",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
                    @ApiResponse(responseCode = "404", description = "An order with associated ID is not found.",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error. Please try again later.",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            }
    )
    @DeleteMapping("/{id}")
    Mono<Void> cancel(
            @RequestHeader(required = true, name = HttpHeaderConstant.X_CLIENT_ID_HEADER) String clientId,
            @Parameter(content = @Content(schema = @Schema(example = "6b8895d1-c2ab-4043-978a-631b6d2fdfb9"))) @PathVariable UUID id
    );

    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Make an order",
            parameters = {
                    @Parameter(required = true,
                            name = HttpHeaderConstant.X_CLIENT_ID_HEADER,
                            description = "A client's ID (a service name)",
                            schema = @Schema(example = "warehouse-microservice"))
            },
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "An order successfully created.",
                            headers = {
                                    @Header(
                                            name = HttpHeaders.LOCATION,
                                            description = "The URL (location) of a newly created resource",
                                            required = true,
                                            schema = @Schema(type = "string", required = true, example = "/factory/api/v1/orders/4e82f625-199f-41ee-8243-355d3b0356ca")
                                    )
                            }
                    ),
                    @ApiResponse(responseCode = "400", description = "A request is invalid. Some required parameters are missing.",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(type = "object",
                                            example = """
                                                {
                                                    "timestamp": "2023-09-02T02:15:20.670+00:00",
                                                    "path": "/api/v1/orders",
                                                    "status": 400,
                                                    "error": "Bad Request",
                                                    "message": "",
                                                    "requestId": "ba1d0cbd-1"
                                                }
                                                """))),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error. Please try again later.",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            }
    )
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    Mono<ResponseEntity<Void>> create(
            @RequestHeader(required = true, name = HttpHeaderConstant.X_CLIENT_ID_HEADER) String clientId,
            @org.springframework.web.bind.annotation.RequestBody
            @io.swagger.v3.oas.annotations.parameters.RequestBody
            NewOrderRequest newOrder
    );

    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Get an orders report",
            parameters = {
                    @Parameter(required = true, name = HttpHeaderConstant.X_CLIENT_ID_HEADER, description = "A client's ID (a service name)",
                            content = @Content(schema = @Schema(example = "warehouse-microservice"))),
                    @Parameter(required = false, name = HttpHeaderConstant.X_CLIENT_IP_HEADER,
                            description = "A client's IP",
                            schema = @Schema(example = "192.158.1.38")),
            },
            security = @SecurityRequirement(name = "basicAuth"),
            responses = {
                    @ApiResponse(responseCode = "200", description = "A report was successfully created.",
                            content = @Content(schema = @Schema(type = "object", implementation = OrdersReportResponse.class), mediaType = MediaType.APPLICATION_JSON_VALUE)),
                    @ApiResponse(responseCode = "401", description = "Unauthorized access.",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error. Please try again later.",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            }
    )
    @GetMapping(value = REPORT_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
    Mono<OrdersReportResponse> getReport(
            @RequestHeader(name = HttpHeaderConstant.X_CLIENT_ID_HEADER) String clientId,
            @RequestHeader(name = HttpHeaderConstant.X_CLIENT_IP_HEADER, required = false) String clientIp
    );
}
