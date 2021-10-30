package com.showmeyourcode.spring_cloud.client;

import com.showmeyourcode.spring_cloud.client.configuration.ApiPathConstant;
import com.showmeyourcode.spring_cloud.client.dev.IntegrationTestBase;
import com.showmeyourcode.spring_cloud.client.rest.Service1Controller;
import org.junit.jupiter.api.Test;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class Service1ControllerTest extends IntegrationTestBase {

    @Test
    public void shouldGetService1NameUsingOpenFeign() throws Exception {
        this.mockMvc.perform(
                RestDocumentationRequestBuilders
                        .get(ApiPathConstant.SERVICE_1 + Service1Controller.PATH_MANUAL_OPENFEIGN)
        )
                .andExpect(status().isOk())
                .andExpect(content().string("EXAMPLE-RESPONSE-SERVICE1-MOCK"))
                .andDo(
                        document("service1/getMicroserviceName",
                                preprocessRequest(prettyPrint()),
                                preprocessResponse(prettyPrint()))
                );
    }
}
