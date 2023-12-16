package com.showmeyourcode.spring_cloud.demo.reporting.graphql.resolver;

import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;

/**
 * Sample data which are going to be replaced when proper logic for GraphQL is implemented.
 */
@Component
@RequiredArgsConstructor
public class ItemMutationResolver implements GraphQLMutationResolver {

    public String addItem(String param) {
        return Instant.now().toString();
    }
}
