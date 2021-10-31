package com.showmeyourcode.spring_cloud.admin;

import de.codecentric.boot.admin.server.config.AdminServerHazelcastAutoConfiguration;
import de.codecentric.boot.admin.server.config.EnableAdminServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@Slf4j
@EnableEurekaClient
@EnableAdminServer
@SpringBootApplication(exclude = AdminServerHazelcastAutoConfiguration.class)
public class SpringBootCloudAdminApplication {

	public static void main(String[] args) {
		log.info("Starting Admin dashboard...");
		SpringApplication.run(SpringBootCloudAdminApplication.class, args);
		log.info("Admin dashboard started!");
	}
}
