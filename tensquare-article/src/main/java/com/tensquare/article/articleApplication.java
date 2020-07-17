package com.tensquare.article;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import uitl.IdWorker;

@SpringBootApplication
public class articleApplication {
    public static void main(String[] args) {
        SpringApplication.run(articleApplication.class,args);
    }

    @Bean
    public IdWorker idWorker(){
        return new IdWorker();
    }
}
