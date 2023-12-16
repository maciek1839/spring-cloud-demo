package com.showmeyourcode.spring_cloud.demo.reporting.graphql.resolver;

import com.showmeyourcode.spring_cloud.demo.reporting.graphql.model.ServicesInfoOverview;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Sample data which are going to be replaced when proper logic for GraphQL is implemented.
 */
@Component
@RequiredArgsConstructor
public class ServicesInfoQueryResolver implements GraphQLQueryResolver {

    public ServicesInfoOverview getServicesInfo(){
        return new ServicesInfoOverview("sample","sample","sample");
    }
}
