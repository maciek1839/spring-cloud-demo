package com.showmeyourcode.spring_cloud.eureka_server;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.hamcrest.Matchers.notNullValue;

@SpringBootTest
class EurekaApplicationIT {

    @Autowired
    private ApplicationContext context;

    @Test
    void shouldStartEurekaServer() {
        MatcherAssert.assertThat(context.getBean("eurekaServerContext"), Matchers.is(notNullValue()));
    }
}
