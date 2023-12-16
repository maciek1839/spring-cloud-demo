package com.showmeyourcode.spring_cloud.demo.reporting.graphql.error;

import graphql.ErrorClassification;
import graphql.ExceptionWhileDataFetching;
import graphql.GraphQLError;
import graphql.language.SourceLocation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GraphQLErrorAdapter implements GraphQLError {

    private GraphQLError error;

    @Override
    public ErrorClassification getErrorType() {
        return error.getErrorType();
    }

    @Override
    public Map<String, Object> getExtensions() {
        return error.getExtensions();
    }

    @Override
    public List<SourceLocation> getLocations() {
        return error.getLocations();
    }

    @Override
    public String getMessage() {
        return (error instanceof ExceptionWhileDataFetching)
                ? ((ExceptionWhileDataFetching) error).getException().getMessage()
                : error.getMessage();
    }

    @Override
    public List<Object> getPath() {
        return error.getPath();
    }

    @Override
    public Map<String, Object> toSpecification() {
        return error.toSpecification();
    }
}
