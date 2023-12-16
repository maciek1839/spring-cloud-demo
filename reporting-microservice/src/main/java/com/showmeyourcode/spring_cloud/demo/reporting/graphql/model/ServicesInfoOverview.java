package com.showmeyourcode.spring_cloud.demo.reporting.graphql.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Sample data which are going to be replaced when proper logic for GraphQL is implemented.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServicesInfoOverview {

    private String factory;
    private String shop;
    private String warehouse;
}
