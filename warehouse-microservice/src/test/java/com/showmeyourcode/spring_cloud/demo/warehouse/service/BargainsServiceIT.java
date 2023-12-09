package com.showmeyourcode.spring_cloud.demo.warehouse.service;

import com.showmeyourcode.spring_cloud.demo.warehouse.BaseIT;
import com.showmeyourcode.spring_cloud.demo.warehouse.constant.EndpointConstant;
import com.showmeyourcode.spring_cloud.demo.warehouse.grpc.BargainProto;
import com.showmeyourcode.spring_cloud.demo.warehouse.grpc.BargainsServiceGrpc;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

@Slf4j
class BargainsServiceIT extends BaseIT {

    @GrpcClient("bargains")
    private BargainsServiceGrpc.BargainsServiceBlockingStub bargainsStub;

    @Test
    void shouldReturnBargainsUsingGrpc() {
        BargainProto.BargainFindAllResponse response = bargainsStub.findAll(BargainProto.Empty.newBuilder().build());

        MatcherAssert.assertThat(response.getMessageCode(), Matchers.is(HttpStatus.OK.value()));
        MatcherAssert.assertThat(response.getProductsCount(), Matchers.is(10));

        var product = response.getProducts(0);
        MatcherAssert.assertThat(product.getBasePrice(), Matchers.notNullValue());
        MatcherAssert.assertThat(product.getId(), Matchers.notNullValue());
        MatcherAssert.assertThat(product.getDiscount(), Matchers.notNullValue());
    }
}
