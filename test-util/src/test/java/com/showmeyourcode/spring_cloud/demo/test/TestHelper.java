package com.showmeyourcode.spring_cloud.demo.test;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ExchangeFunction;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;


import java.time.Duration;

@Slf4j
public class TestHelper {

    static ObjectMapper mapper = JsonMapper.builder()
            .build()
            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .registerModules(new JavaTimeModule());

    static WebTestClient reportingMicroserviceWebClient(){
        return buildWebTestClient("http://localhost:8000/reporting");
    }

    static WebTestClient shopMicroserviceWebClient() {
        return buildWebTestClient("http://localhost:8100/shop");
    }

    static WebTestClient factoryMicroserviceWebClient(){
        return buildWebTestClient("http://localhost:8300/factory");
    }

    static WebTestClient warehouseMicroserviceWebClient() {
        return buildWebTestClient("http://localhost:8200/warehouse");
    }

    static WebTestClient eurekaMicroserviceWebClient(){
        return buildWebTestClient("http://localhost:8761");
    }

    static WebTestClient adminMicroserviceWebClient(){
        return buildWebTestClient("http://localhost:9000");
    }

    private static WebTestClient buildWebTestClient(String s) {
        return WebTestClient.bindToServer(
                        new ReactorClientHttpConnector(HttpClient.create(provider))
                )
                .codecs(configurer -> {
                    configurer.defaultCodecs().jackson2JsonEncoder(new Jackson2JsonEncoder(mapper, MediaType.APPLICATION_JSON));
                    configurer.defaultCodecs().jackson2JsonDecoder(new Jackson2JsonDecoder(mapper, MediaType.APPLICATION_JSON));
                })
                .filter(TestHelper::loggingFilter)
                .defaultHeader(HttpHeaders.USER_AGENT, "System/Acceptance tests client")
                .defaultHeader(InfrastructureConstant.X_CLIENT_ID, "test-client")
                .baseUrl(s)
                .build();
    }

    private static Mono<ClientResponse> loggingFilter(ClientRequest clientRequest, ExchangeFunction exchangeFunction) {
        log.info(
                String.format(
                """
                \n
                |
                | %s %s
                |
                | %s
                |
                """,
                        clientRequest.method(),
                        clientRequest.url(),
                        clientRequest.headers()
                )
        );
        return exchangeFunction.exchange(clientRequest);
    }

    private static final ConnectionProvider provider = ConnectionProvider.builder("optimized")
            .maxConnections(500)
            .maxIdleTime(Duration.ofSeconds(5))
            .maxLifeTime(Duration.ofSeconds(60))
            .pendingAcquireTimeout(Duration.ofSeconds(60))
            .evictInBackground(Duration.ofSeconds(30))
            .build();
}
