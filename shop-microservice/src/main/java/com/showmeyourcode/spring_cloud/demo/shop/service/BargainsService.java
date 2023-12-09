package com.showmeyourcode.spring_cloud.demo.shop.service;

import com.showmeyourcode.spring_cloud.demo.shop.grpc.BargainProto;
import com.showmeyourcode.spring_cloud.demo.shop.grpc.BargainsServiceGrpc;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BargainsService {

    @GrpcClient("bargains")
    private BargainsServiceGrpc.BargainsServiceBlockingStub bargainsStub;

    public ResponseEntity<Integer> getBargains(){
        log.info("Calling a gRPC server to get bargains...");
        var products = bargainsStub.findAll(BargainProto.Empty.newBuilder().build()).getProductsList();
        log.info("Got products: {}", products.size());
        return ResponseEntity.ok(products.size());
    }

    // for testing purposes
    void setBargainsStub(BargainsServiceGrpc.BargainsServiceBlockingStub stub){
        this.bargainsStub = stub;
    }
}
