package com.socialCloneMicroservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class SocialCloneMicroservicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(SocialCloneMicroservicesApplication.class, args);
	}

}
