package com.showmeyourcode.spring_cloud.client.api;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.showmeyourcode.spring_cloud.client.BaseIT;
import com.showmeyourcode.spring_cloud.client.configuration.ApiPathConstant;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.configureFor;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class Service1ProxyEndpointIT extends BaseIT {

    @Test
    void shouldCallService1UsingFeign() throws Exception {
        configureFor(wireMockServer.port());
        stubFor(WireMock.get(urlEqualTo("/microservice1/api/v1/"))
                .willReturn(aResponse().withStatus(200).withBody("microservice1"))
        );

        var uri = ApiPathConstant.SERVICE_1 + Service1ProxyEndpoint.PATH_MANUAL_OPENFEIGN;

        this.mockMvc.perform(MockMvcRequestBuilders.get(uri))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$").exists())
                .andDo(document("service1-proxy/getMicroserviceName"));
    }
}