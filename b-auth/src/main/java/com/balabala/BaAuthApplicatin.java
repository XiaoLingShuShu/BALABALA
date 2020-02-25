package com.balabala;

import com.balabala.config.JwtProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @ProjectName: BALABALA
 * @Package: com.balabala
 * @ClassName: BaAuthApplicatin
 * @Author: Administrator
 * @Description: ${description}
 * @Date: 2020/2/7 16:32
 * @Version: 1.0
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableConfigurationProperties(JwtProperties.class)
public class BaAuthApplicatin {
    public static void main(String[] args) {
        SpringApplication.run(BaAuthApplicatin.class,args);
    }
}
