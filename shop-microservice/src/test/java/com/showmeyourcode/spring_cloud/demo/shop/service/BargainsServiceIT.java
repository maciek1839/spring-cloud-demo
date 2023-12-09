package com.showmeyourcode.spring_cloud.demo.shop.service;


import com.showmeyourcode.spring_cloud.demo.shop.BaseIT;
import com.showmeyourcode.spring_cloud.demo.shop.api.WarehouseProxyEndpointSpecification;
import com.showmeyourcode.spring_cloud.demo.shop.constant.HttpHeaderConstant;
import io.restassured.RestAssured;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

@Slf4j
class BargainsServiceIT extends BaseIT {

    @Test
    void shouldReturnBargainsUsingGrpc() {
        var uri = WarehouseProxyEndpointSpecification.ENDPOINT_PATH + WarehouseProxyEndpointSpecification.BARGAINS_WAREHOUSE_PATH;

        RestAssured.given(this.requestSpecification)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaderConstant.X_CLIENT_ID_HEADER, exampleClientHeaderValue)
                .when()
                .get(addContextPath(uri))
                .then()
                .assertThat()
                .statusCode(Matchers.is(HttpStatus.INTERNAL_SERVER_ERROR.value()));
        // todo: find out a way to test the gRPC client
    }
}
