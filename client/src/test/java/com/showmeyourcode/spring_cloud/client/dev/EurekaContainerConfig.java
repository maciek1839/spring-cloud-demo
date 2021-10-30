package com.showmeyourcode.spring_cloud.client.dev;

import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.lifecycle.Startables;

import java.util.stream.Stream;

public class EurekaContainerConfig {
    public static class Initializer implements ApplicationContextInitializer {

        public static GenericContainer eurekaServer = new GenericContainer("springcloud/eureka")
                .withExposedPorts(8761);

        @Override
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {

            Startables.deepStart(Stream.of(eurekaServer)).join();

            TestPropertyValues
                    .of("eureka.client.serviceUrl.defaultZone=http://localhost:"
                            + eurekaServer.getFirstMappedPort().toString()
                            + "/eureka")
                    .applyTo(configurableApplicationContext);
        }
    }
}
