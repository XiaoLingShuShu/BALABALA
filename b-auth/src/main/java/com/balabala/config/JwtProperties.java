package com.balabala.config;

import com.balabala.utils.RsaUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * @ProjectName: BALABALA
 * @Package: com.balabala.config
 * @ClassName: JwtProperties
 * @Author: Administrator
 * @Description: ${description}
 * @Date: 2020/2/7 16:41
 * @Version: 1.0
 */
@Data
@Slf4j
@ConfigurationProperties(prefix = "ba.jwt")
public class JwtProperties implements InitializingBean {

    /**
     * 公私钥地址
     */
    private String pubKeyPath;
    private String priKeyPath;

    /**
     * 公私钥对象
     */
    private PublicKey publicKey;
    private PrivateKey privateKey;

    /**
     * 用户token相关属性
     */
    private UserTokenProperties user = new UserTokenProperties();

    @Data
    public class UserTokenProperties{
        /**
         * token过期时长
         */
        private int expire;
        /**
         * 存放token的cookie名称
         */
        private String cookieName;
        /**
         * 存放token的cookie的domain
         */
        private String cookieDomain;
        /**
         * cookie最小刷新时间
         */
        private int minRefreshInterval;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        try {
            // 获取公钥和私钥
            this.publicKey = RsaUtils.getPublicKey(pubKeyPath);
            this.privateKey = RsaUtils.getPrivateKey(priKeyPath);
        } catch (Exception e) {
            log.error("初始化公钥和私钥失败！", e);
            throw new RuntimeException(e);
        }
    }
}
