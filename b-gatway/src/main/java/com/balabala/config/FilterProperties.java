package com.balabala.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.util.List;

/**
 * @ProjectName: BALABALA
 * @Package: com.balabala.config
 * @ClassName: FilterProperties
 * @Author: Administrator
 * @Description: ${description}
 * @Date: 2020/2/9 21:23
 * @Version: 1.0
 */
@ConfigurationProperties(prefix = "bala.filter")
public class FilterProperties {

    private List<String> allowPaths;

    public List<String> getAllowPaths() {
        return allowPaths;
    }

    public void setAllowPaths(List<String> allowPaths) {
        this.allowPaths = allowPaths;
    }
}
