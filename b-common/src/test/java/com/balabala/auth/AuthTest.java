package com.balabala.auth;

import com.balabala.pojo.UserInfo;
import com.balabala.utils.JwtUtils;
import com.balabala.utils.RsaUtils;
import org.junit.Test;

import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * @ProjectName: BALABALA
 * @Package: com.balabala.auth
 * @ClassName: AuthTest
 * @Author: Administrator
 * @Description: ${description}
 * @Date: 2020/2/7 16:23
 * @Version: 1.0
 */
public class AuthTest {

    private String privateFilePath = "G:\\develop\\id_rsa";
    private String publicFilePath = "G:\\develop\\id_rsa.pub";

    @Test
    public void testRSA() throws Exception {
        // 生成密钥对
        RsaUtils.generateKey(publicFilePath, privateFilePath, "hello", 2048);

        // 获取私钥
        PrivateKey privateKey = RsaUtils.getPrivateKey(privateFilePath);
        System.out.println("privateKey = " + privateKey);
        // 获取公钥
        PublicKey publicKey = RsaUtils.getPublicKey(publicFilePath);
        System.out.println("publicKey = " + publicKey);
    }

    @Test
    public void testJWT() throws Exception {
        // 获取私钥
        PrivateKey privateKey = RsaUtils.getPrivateKey(privateFilePath);
        // 生成token
        String token = JwtUtils.generateTokenExpireInMinutes(new UserInfo(1L, "Jack", "guest","123"), privateKey, 5);
        System.out.println("token = " + token);

        // 获取公钥
        PublicKey publicKey = RsaUtils.getPublicKey(publicFilePath);
        // 解析token
        Payload<UserInfo> info = JwtUtils.getInfoFromToken(token, publicKey, UserInfo.class);

        System.out.println("info.getExpiration() = " + info.getExpiration());
        System.out.println("info.getUserInfo() = " + info.getUserInfo());
        System.out.println("info.getId() = " + info.getId());
    }

}
