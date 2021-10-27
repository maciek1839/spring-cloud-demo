package com.showmeyourcode.spring_cloud.microservice1;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class SpringBootCloudMicroservice1Application {

	public static void main(String[] args) {
		log.info("Starting Microservice1...");
		SpringApplication.run(SpringBootCloudMicroservice1Application.class, args);
		log.info("Microservice1 server started!");
	}
}
