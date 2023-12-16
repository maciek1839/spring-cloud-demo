package com.showmeyourcode.spring_cloud.demo.shop.service;


import com.google.protobuf.MessageOrBuilder;
import com.showmeyourcode.spring_cloud.demo.shop.BaseIT;
import com.showmeyourcode.spring_cloud.demo.shop.api.WarehouseProxyEndpointSpecification;
import com.showmeyourcode.spring_cloud.demo.shop.constant.HttpHeaderConstant;
import com.showmeyourcode.spring_cloud.demo.shop.grpc.BargainProto;
import com.showmeyourcode.spring_cloud.demo.shop.grpc.BargainsServiceGrpc;
import io.restassured.RestAssured;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.ArrayList;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Slf4j
class BargainsServiceIT extends BaseIT {

    @Autowired
    private BargainsService bargainsService;

    @Test
    void shouldReturnBargainsUsingGrpc() {
        var stub = mock(BargainsServiceGrpc.BargainsServiceBlockingStub.class);
        var products = new ArrayList<BargainProto.BargainProduct>();
        products.add(Mockito.mock(BargainProto.BargainProduct.class));
        products.add(BargainProto.BargainProduct.newBuilder().setBasePrice(12.22).setDiscount(0.45).setId(UUID.randomUUID().toString()).build());
        when(stub.findAll(any())).thenReturn(
                BargainProto.BargainFindAllResponse.newBuilder().setMessageCode(200).addAllProducts(products).build()
        );
        bargainsService.setBargainsStub(stub);

        var uri = WarehouseProxyEndpointSpecification.ENDPOINT_PATH + WarehouseProxyEndpointSpecification.BARGAINS_WAREHOUSE_PATH;

        RestAssured.given(this.requestSpecification)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaderConstant.X_CLIENT_ID_HEADER, exampleClientHeaderValue)
                .when()
                .get(addContextPath(uri))
                .then()
                .assertThat()
                .statusCode(Matchers.is(HttpStatus.OK.value()));
    }
}
