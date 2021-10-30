package com.showmeyourcode.spring_cloud.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@Slf4j
@EnableFeignClients(basePackages = {
		"io.swagger.api",
		"com.showmeyourcode.spring_cloud.client"
})
@SpringBootApplication
public class SpringBootCloudClientApplication {



	public static void main(String[] args) {
		log.info("Starting Client...");
		SpringApplication.run(SpringBootCloudClientApplication.class, args);
		log.info("Client started!");
	}
}
