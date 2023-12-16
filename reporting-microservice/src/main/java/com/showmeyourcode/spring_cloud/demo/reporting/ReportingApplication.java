package com.showmeyourcode.spring_cloud.demo.reporting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(basePackages = {
		"com.showmeyourcode.spring_cloud.demo.reporting.configuration"
})
@SpringBootApplication
public class ReportingApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReportingApplication.class, args);
	}
}
