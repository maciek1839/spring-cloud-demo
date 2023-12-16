package com.showmeyourcode.spring_cloud.demo.reporting.graphql.resolver;

import com.showmeyourcode.spring_cloud.demo.reporting.configuration.ShopApiClient;
import com.showmeyourcode.spring_cloud.demo.reporting.constant.ClientConstant;
import com.showmeyourcode.spring_cloud.demo.reporting.generated.shop.model.NewOrderRequest;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Slf4j
@Component
@RequiredArgsConstructor
public class ShopMutationResolver implements GraphQLMutationResolver {

    private final ShopApiClient client;

    public int makeShopOrder(NewOrderRequest orderRequest) {
        log.info("Adding a new shop item: {}", orderRequest);
        return client.create(ClientConstant.MICROSERIVCE_ID, orderRequest).getStatusCode().value();
    }
}
