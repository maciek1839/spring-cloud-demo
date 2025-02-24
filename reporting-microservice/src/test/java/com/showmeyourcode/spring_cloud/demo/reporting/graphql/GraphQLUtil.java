package com.showmeyourcode.spring_cloud.demo.reporting.graphql;

public class GraphQLUtil {

    String getNotExistingQuery = """
            {"query":"query {\\n    getNotFoundItems {\\n        id\\n        name\\n    }\\n}\\n"}
            """;

    String getShopItemsPayload = """
            {"query":"query {\\n    getShopItems {\\n        id\\n        name\\n    }\\n}\\n"}
            """;

    String getWarehouseItemsPayload = """
            {"query":"query {\\n    getWarehouseItems {\\n        id\\n        name\\n    }\\n}\\n"}
            """;


    String mutateShopOrder = "{\"query\":\"" + "mutation { makeShopOrder ( order: { item: { name: \\\"item name\\\" } customerName: \\\"customer name\\\" comment: \\\"order comment\\\" })}" + "\"}";
}
