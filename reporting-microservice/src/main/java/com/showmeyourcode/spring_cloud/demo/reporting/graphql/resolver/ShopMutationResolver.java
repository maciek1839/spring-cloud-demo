package com.showmeyourcode.spring_cloud.demo.reporting.graphql.resolver;

import com.showmeyourcode.spring_cloud.demo.reporting.configuration.ShopApiClient;
import com.showmeyourcode.spring_cloud.demo.reporting.constant.ClientConstant;
import com.showmeyourcode.spring_cloud.demo.reporting.generated.shop.model.NewOrderRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ShopMutationResolver {

    private final ShopApiClient client;

    @MutationMapping
    public int makeShopOrder(@Argument NewOrderRequest order) {
        log.info("Adding a new shop item: {}", order);
        return client.create(ClientConstant.MICROSERIVCE_ID, order).getStatusCode().value();
    }
}
