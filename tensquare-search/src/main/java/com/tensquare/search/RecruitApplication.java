package com.tensquare.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import uitl.IdWorker;

@SpringBootApplication
public class RecruitApplication {
    public static void main(String[] args) {
        SpringApplication.run(RecruitApplication.class, args);
    }
    @Bean
    public IdWorker idWorkker(){
        return new IdWorker(1, 1);
    }
}
