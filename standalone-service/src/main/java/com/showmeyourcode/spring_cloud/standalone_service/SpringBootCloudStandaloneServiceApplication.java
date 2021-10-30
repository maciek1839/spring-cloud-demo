package com.showmeyourcode.spring_cloud.standalone_service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class SpringBootCloudStandaloneServiceApplication {

	public static void main(String[] args) {
		log.info("Starting Standalone service...");
		SpringApplication.run(SpringBootCloudStandaloneServiceApplication.class, args);
		log.info("Standalone service started!");
	}
}
