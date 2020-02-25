package com.balabala;

import com.balabala.config.FilterProperties;
import com.netflix.zuul.ZuulFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@EnableZuulProxy
@SpringCloudApplication
//@ComponentScan(basePackages = "com.balabala.config")
public class BaGatway {
    public static void main(String[] args) {
        SpringApplication.run(BaGatway.class,args);
    }
    @Bean
    public FilterProperties simpleFilter(){
        return new FilterProperties();
    }
}
