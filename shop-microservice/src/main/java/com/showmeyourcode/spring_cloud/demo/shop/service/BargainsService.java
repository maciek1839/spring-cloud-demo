package com.showmeyourcode.spring_cloud.demo.shop.service;



import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.util.JsonFormat;
import com.showmeyourcode.spring_cloud.demo.shop.grpc.BargainProto;
import com.showmeyourcode.spring_cloud.demo.shop.grpc.BargainsServiceGrpc;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class BargainsService {


    @GrpcClient("bargains")
    private BargainsServiceGrpc.BargainsServiceBlockingStub bargainsStub;
    private final JsonFormat.Printer PRINTER = JsonFormat.printer();
    private final ObjectMapper mapper;

    public BargainsService(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    public List<JsonNode> getBargains(){
        log.info("Calling a gRPC server to get bargains...");
        var products = bargainsStub.findAll(BargainProto.Empty.newBuilder().build()).getProductsList();
        log.info("Got products: {}", products.size());
        return products.stream().map(message -> {
            try {
                return mapper.readTree(PRINTER.print(message));
            } catch (Exception e) {
                log.warn("Cannot parse the protobuf object! Id: {} ", message.getId(),e);
                return null;
            }
        }).filter(Objects::nonNull).toList();
    }

    // for testing purposes
    void setBargainsStub(BargainsServiceGrpc.BargainsServiceBlockingStub stub){
        this.bargainsStub = stub;
    }
}
