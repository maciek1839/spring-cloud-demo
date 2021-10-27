package com.showmeyourcode.spring_cloud.eureka_server;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@Slf4j
@EnableEurekaServer
@SpringBootApplication
public class SpringBootCloudEurekaApplication {

	public static void main(String[] args) {
		log.info("Starting Eureka server...");
		SpringApplication.run(SpringBootCloudEurekaApplication.class, args);
		log.info("Eureka server started!");
	}
}
