package com.showmeyourcode.spring_cloud.demo.reporting.graphql.resolver;

import com.showmeyourcode.spring_cloud.demo.reporting.configuration.ShopApiClient;
import com.showmeyourcode.spring_cloud.demo.reporting.constant.ClientConstant;
import com.showmeyourcode.spring_cloud.demo.reporting.generated.shop.model.ItemResponse;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Sample data which are going to be replaced when proper logic for GraphQL is implemented.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class ShopQueryResolver implements GraphQLQueryResolver {

    private final ShopApiClient client;

    public List<ItemResponse> getShopItems(){
        log.info("Getting shop items...");
        return client.getAll(ClientConstant.MICROSERIVCE_ID).getBody();
    }
}
