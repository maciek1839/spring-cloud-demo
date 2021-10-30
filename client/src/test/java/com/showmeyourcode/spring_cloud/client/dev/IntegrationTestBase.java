package com.showmeyourcode.spring_cloud.client.dev;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.showmeyourcode.spring_cloud.client.dev.service.Service1Mocks;
import com.showmeyourcode.spring_cloud.client.dev.service.WireMockConfig;
import org.junit.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;

@SpringBootTest
@ActiveProfiles("test")
@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@ContextConfiguration(classes = {WireMockConfig.class})
public abstract class IntegrationTestBase {

    @Rule
    public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation("target/generated-snippets");

    protected MockMvc mockMvc;

    @Autowired
    protected WebApplicationContext context;
    @Autowired
    private WireMockServer wireMockServer;

    @BeforeEach
    public void setUp(RestDocumentationContextProvider restDocumentation) {
        Service1Mocks.setupMockService1Response(wireMockServer);

        this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .apply(documentationConfiguration(restDocumentation))
                .build();
    }
}
