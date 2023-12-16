package com.showmeyourcode.spring_cloud.demo.reporting.graphql;

public class GraphQLUtil {

    String buildWrongMutation() {
        return """
                    mutation {
                        doesNotExist(
                            id: \\"123-123-123\\"
                        )
                    }
                """;
    }

    String buildQuery(String queryToExecute) {
        return "{ \"query\": \"" + queryToExecute + "\", \"variables\": null}";
    }
}
