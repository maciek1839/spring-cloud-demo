package com.showmeyourcode.spring_cloud.microservice1.constant;

public class EndpointConstant {

    public static String ACTUATOR_ENDPOINT = "/actuator";
    public static String ACTUATOR_HEALTH_ENDPOINT = "/actuator/health";
    public static String ACTUATOR_INFO_ENDPOINT = "/actuator/info";
    public static String ACTUATOR_METRICS_ENDPOINT = "/actuator/metrics";

    public static String SWAGGER_UI = "/swagger-ui/";
    public static String SWAGGER_API_DOC = "/v2/api-docs";

    private EndpointConstant() {
    }
}
