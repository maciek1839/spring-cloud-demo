package com.showmeyourcode.spring_cloud.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(basePackages = {
		"io.swagger.api",
		"com.showmeyourcode.spring_cloud.client"
})
@SpringBootApplication
public class SpringBootCloudClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootCloudClientApplication.class, args);
	}
}
