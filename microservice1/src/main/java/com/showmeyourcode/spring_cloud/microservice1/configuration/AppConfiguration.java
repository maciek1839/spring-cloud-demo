package com.showmeyourcode.spring_cloud.microservice1.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Slf4j
@Configuration
public class AppConfiguration {

    @Value("${spring.application.name}")
    private String appName;

    @PostConstruct
    private void printAppName(){
        log.info("App '{}' started!", appName);
    }
}
