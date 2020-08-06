package com.tensquare.spit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import uitl.IdWorker;

@EnableEurekaClient
@SpringBootApplication
public class SpitApplication {

	public static void main(String[] args) {

		SpringApplication.run(SpitApplication.class,args);
	}

	@Bean
	public IdWorker idWorkker(){
		return new IdWorker(1, 1);
	}
	
}
