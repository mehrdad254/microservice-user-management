package com.mhr.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MicroserviceUserManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceUserManagementApplication.class, args);
	}

}
