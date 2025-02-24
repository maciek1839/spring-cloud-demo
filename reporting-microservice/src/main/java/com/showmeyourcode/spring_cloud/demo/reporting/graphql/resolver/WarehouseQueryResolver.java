package com.showmeyourcode.spring_cloud.demo.reporting.graphql.resolver;

import com.showmeyourcode.spring_cloud.demo.reporting.configuration.WarehouseApiClient;
import com.showmeyourcode.spring_cloud.demo.reporting.generated.warehouse.model.ItemResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * Sample data which are going to be replaced when proper logic for GraphQL is implemented.
 */
@Slf4j
@Controller
@RequiredArgsConstructor
public class WarehouseQueryResolver {

    private final WarehouseApiClient client;

    @QueryMapping
    public List<ItemResponse> getWarehouseItems() {
        log.info("Getting warehouse items...");
        return client.getAll("reporting-microservice-id").getBody();
    }
}
