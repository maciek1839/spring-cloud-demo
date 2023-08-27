package com.showmeyourcode.spring_cloud.eureka_server;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class EurekaApplicationTest {

    @Autowired
    private ApplicationContext context;

    @Test
    void shouldStartEurekaServer() {
        assertThat(context.getBean("eurekaServerContext")).isNotNull();
    }

}
