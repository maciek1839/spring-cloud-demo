package com.showmeyourcode.spring_cloud.admin;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.hamcrest.Matchers.notNullValue;

@SpringBootTest
class AdminApplicationIT {

    @Autowired
    private ApplicationContext context;

    @Test
    void shouldStartAdminServer() {
        MatcherAssert.assertThat(context.getBean("discoveryClient"), Matchers.is(notNullValue()));
    }

}
