package com.showmeyourcode.spring_cloud.demo.admin;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class AdminApplicationIT extends BaseIT {

    @Test
    void shouldStartAdminServerWithoutErrors() {
        // For integration tests Eureka discovery is disabled (see application-test.yml).
        MatcherAssert.assertThat(context.containsBean("discoveryClient"), Matchers.is(false));

        // Ensure that cloud components are created.
        // It's also a good way to investigate what beans were created automatically in case of debugging any configuration errors.
        MatcherAssert.assertThat(
                Arrays.stream(context.getBeanDefinitionNames()).filter(e->e.contains("cloud")).count(),
                Matchers.greaterThan(0L)
        );
    }
}
