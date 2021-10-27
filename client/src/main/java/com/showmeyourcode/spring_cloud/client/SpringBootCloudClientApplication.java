package com.showmeyourcode.spring_cloud.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@Slf4j
@EnableFeignClients
@SpringBootApplication
public class SpringBootCloudClientApplication {

	public static void main(String[] args) {
		log.info("Starting Client...");
		SpringApplication.run(SpringBootCloudClientApplication.class, args);
		log.info("Client started!");
	}
}
