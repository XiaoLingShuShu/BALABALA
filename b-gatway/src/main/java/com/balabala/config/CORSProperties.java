package com.balabala.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * HuGe
 */
@Data
@ConfigurationProperties(prefix = "bala.cors")
public class CORSProperties {
    private List<String> allowedOrigins;
    private Boolean allowedCredentials;
    private List<String> allowedMethods;
    private List<String> allowedHeaders;
    private Long maxAge;
    private String filterPath;
}