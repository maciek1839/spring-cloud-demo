package com.showmeyourcode.spring_cloud.demo.reporting.graphql.resolver;

import com.showmeyourcode.spring_cloud.demo.reporting.configuration.ShopApiClient;
import com.showmeyourcode.spring_cloud.demo.reporting.constant.ClientConstant;
import com.showmeyourcode.spring_cloud.demo.reporting.generated.shop.model.ItemResponse;
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
public class ShopQueryResolver {

    private final ShopApiClient client;

    @QueryMapping
    public List<ItemResponse> getShopItems() {
        log.info("Getting shop items...");
        return client.getAll(ClientConstant.MICROSERIVCE_ID).getBody();
    }
}
