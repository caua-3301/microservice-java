package com.developer.cardmiscroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class CardmiscroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CardmiscroserviceApplication.class, args);
	}

}
