package com.showmeyourcode.spring_cloud.demo.reporting.constant;

public class EndpointConstant {

    public static final String ACTUATOR_ENDPOINT = "/actuator";
    public static final String ACTUATOR_HEALTH_ENDPOINT = "/actuator/health";
    public static final String ACTUATOR_INFO_ENDPOINT = "/actuator/info";
    public static final String ACTUATOR_METRICS_ENDPOINT = "/actuator/metrics";

    public static final String SWAGGER_UI = "/swagger-ui.html";
    public static final String SWAGGER_DOCS = "/v3/api-docs.yaml";

    public static final String API_PREFIX = "/api/v1";
    public static final String WAREHOUSE_MICROSERVICE = API_PREFIX + "/warehouse";

    private EndpointConstant() {
    }
}
