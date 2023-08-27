package com.showmeyourcode.spring_cloud.admin;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class AdminApplicationIT {

    @Autowired
    private ApplicationContext context;

    @Test
    void shouldStartAdminServer() {
        assertThat(context.getBean("discoveryClient")).isNotNull();
    }

}