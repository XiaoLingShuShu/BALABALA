package com.balabala.config;

import com.balabala.utils.RsaUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.security.PublicKey;

/**
 * @ProjectName: BALABALA
 * @Package: com.balabala.config
 * @ClassName: JwtProperties
 * @Author: Administrator
 * @Description: ${description}
 * @Date: 2020/2/9 20:45
 * @Version: 1.0
 */
@Data
@Slf4j
@ConfigurationProperties(prefix = "bala.jwt")
public class JwtProperties implements InitializingBean {
    /**
     * 公钥地址
     */
    private String pubKeyPath;

    private PublicKey publicKey;
    /**
     * 用户token相关属性
     */
    private UserTokenProperties user = new UserTokenProperties();

    @Data
    public class UserTokenProperties {
        /**
         * 存放token的cookie名称
         */
        private String cookieName;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        try {
            // 获取公钥和私钥
            this.publicKey = RsaUtils.getPublicKey(pubKeyPath);
        } catch (Exception e) {
            log.error("初始化公钥失败！", e);
            throw new RuntimeException(e);
        }
    }
}
