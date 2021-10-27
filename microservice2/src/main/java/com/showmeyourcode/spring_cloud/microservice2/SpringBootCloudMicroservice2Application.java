package com.showmeyourcode.spring_cloud.microservice2;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class SpringBootCloudMicroservice2Application {

	public static void main(String[] args) {
		log.info("Starting Microservice2...");
		SpringApplication.run(SpringBootCloudMicroservice2Application.class, args);
		log.info("Microservice2 started!");
	}
}
