package com.showmeyourcode.spring_cloud.demo.shop.configuration;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@EnableFeignClients(basePackages = {
        "com.showmeyourcode.spring_cloud.demo.shop.configuration"
})
@Configuration
public class OpenFeignConfiguration {
}
