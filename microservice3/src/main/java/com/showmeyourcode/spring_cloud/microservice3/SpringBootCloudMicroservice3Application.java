package com.showmeyourcode.spring_cloud.microservice3;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class SpringBootCloudMicroservice3Application {

	public static void main(String[] args) {
		log.info("Starting Microservice3...");
		SpringApplication.run(SpringBootCloudMicroservice3Application.class, args);
		log.info("Microservice3 started!");
	}
}
