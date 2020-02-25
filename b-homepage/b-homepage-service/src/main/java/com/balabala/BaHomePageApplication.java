package com.balabala;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.balabala.mapper")
public class BaHomePageApplication {
    public static void main(String[] args) {
        SpringApplication.run(BaHomePageApplication.class,args);
    }
}
