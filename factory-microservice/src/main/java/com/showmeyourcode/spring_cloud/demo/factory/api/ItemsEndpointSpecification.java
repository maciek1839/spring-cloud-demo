package com.showmeyourcode.spring_cloud.demo.factory.api;

import com.showmeyourcode.spring_cloud.demo.factory.constant.HttpHeaderConstant;
import com.showmeyourcode.spring_cloud.demo.factory.model.ItemResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;


@Tag(name = "Items", description = "Items endpoint")
@RequestMapping(ItemsEndpointSpecification.ENDPOINT_PATH)
public interface ItemsEndpointSpecification {

    String ENDPOINT_PATH = "/api/v1/items";

    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Get all available items",
            parameters = {
                    @Parameter(required = true, name = HttpHeaderConstant.X_CLIENT_ID_HEADER,
                            description = "A client's ID (a service name)",
                            schema = @Schema(example = "warehouse-microservice"))
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved items.",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(type = "object", implementation = ItemResponse.class)))),
                    @ApiResponse(responseCode = "400", description = "A request is invalid. Some required parameters are missing.",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(type = "object",
                                            example = """
                                            {
                                                "timestamp": "2023-09-02T01:19:20.515+00:00",
                                                "path": "/api/v1/items",
                                                "status": 400,
                                                "error": "Bad Request",
                                                "message": "",
                                                "requestId": "2016f8a8-1"
                                            }
                                            """))),

                    @ApiResponse(responseCode = "500", description = "Internal Server Error. Please try again later.",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            }
    )
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    Mono<List<ItemResponse>> getAll(
            @RequestHeader(required = true, name = HttpHeaderConstant.X_CLIENT_ID_HEADER) String clientId
    );

    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Get an item by ID",
            parameters = {
                    @Parameter(required = true,
                            name = HttpHeaderConstant.X_CLIENT_ID_HEADER,
                            description = "A client's ID (a service name)",
                            schema = @Schema(example = "warehouse-microservice"))
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved an item.",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(type = "object", implementation = ItemResponse.class))),
                    @ApiResponse(responseCode = "404", description = "An item with associated ID is not found.",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error. Please try again later.",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            }
    )
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    Mono<ItemResponse> getById(
            @RequestHeader(required = true, name = HttpHeaderConstant.X_CLIENT_ID_HEADER) String clientId,
            @Parameter(content = @Content(schema = @Schema(example = "6b8895d1-c2ab-4043-978a-631b6d2fdfb9"))) @PathVariable UUID id
    );
}
