package com.showmeyourcode.spring_cloud.demo.warehouse.service;

import com.github.javafaker.Faker;
import com.showmeyourcode.spring_cloud.demo.warehouse.grpc.BargainProto;
import com.showmeyourcode.spring_cloud.demo.warehouse.grpc.BargainsServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.ArrayList;
import java.util.List;

@GrpcService
public class BargainsService extends BargainsServiceGrpc.BargainsServiceImplBase {

    private final Faker faker = new Faker();
    private final List<BargainProto.BargainProduct> products = generateProducts();

    @Override
    public void findAll(BargainProto.Empty request, StreamObserver<BargainProto.BargainFindAllResponse> responseObserver) {
        BargainProto.BargainFindAllResponse response = BargainProto.BargainFindAllResponse
                .newBuilder()
                .addAllProducts(products)
                .setMessageCode(200)
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    private List<BargainProto.BargainProduct> generateProducts() {
        var result = new ArrayList<BargainProto.BargainProduct>();

        for (int i = 0; i < 10; i++) {
            result.add(
                    BargainProto.BargainProduct.newBuilder()
                            .setBasePrice(faker.number().randomDouble(2,10,1000))
                            .setDiscount(faker.random().nextInt(1,5).doubleValue()/10d)
                            .setId(faker.internet().uuid())
                            .build()
            );
        }

        return result;
    }

}
