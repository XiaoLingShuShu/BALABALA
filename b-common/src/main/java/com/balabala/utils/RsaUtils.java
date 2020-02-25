package com.balabala.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * @ProjectName: BALABALA
 * @Package: com.balabala.utils
 * @ClassName: RsaUtils
 * @Author: Administrator
 * @Description: ${description}
 * @Date: 2020/2/7 15:25
 * @Version: 1.0
 */
public class RsaUtils {

    private static final int DEFAULT_KEY_SIZE = 2048;

    /**
     * 从文件中读取公钥
     * @param filename 公钥保存路径
     * @return 公钥对象
     * @throws Exception
     */
    public static PublicKey getPublicKey(String filename) throws Exception {
        //读取文件地址并转为字节码形式
        byte[] bytes = readFile(filename);
        //获取公钥并返回公钥对象
        return getPublicKey(bytes);
    }

    /**
     * 获取公钥
     * @param bytes 公钥的字节码形式
     * @return 公钥对象
     * @throws Exception
     */
    private static PublicKey getPublicKey(byte[] bytes) throws Exception {
        //Base64解码
        bytes = Base64.getDecoder().decode(bytes);
        //设置解密规范为RSA
        KeyFactory factory = KeyFactory.getInstance("RSA");
        X509EncodedKeySpec spec = new X509EncodedKeySpec(bytes);
        //根据解密规范返回公钥对象
        return factory.generatePublic(spec);
    }

    /**
     * 从文件中读取私钥
     * @param filename 私钥保存路径
     * @return 私钥对象
     * @throws Exception
     */
    public static PrivateKey getPrivateKey(String filename) throws Exception {
        byte[] bytes = readFile(filename);
        return getPrivateKey(bytes);
    }

    /**
     * 获取私钥
     * @param bytes 私钥的字节码形式
     * @return  私钥的对象
     * @throws Exception
     */
    private static PrivateKey getPrivateKey(byte[] bytes) throws NoSuchAlgorithmException, InvalidKeySpecException {
        bytes = Base64.getDecoder().decode(bytes);
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(bytes);
        KeyFactory factory = KeyFactory.getInstance("RSA");
        return factory.generatePrivate(spec);
    }

    /**
     * 根据密文，生成rsa公钥和私钥，并写入指定文件
     * @param publicKeyFileName 公钥文件路径
     * @param privateKeyFileName 私钥文件路径
     * @param secret 生成密钥的密文
     * @param keySize 生成密钥的长度
     * @throws Exception
     */
    public static void generateKey(String publicKeyFileName,String privateKeyFileName,String secret,int keySize) throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        SecureRandom secureRandom = new SecureRandom(secret.getBytes());
        keyPairGenerator.initialize(Math.max(keySize, DEFAULT_KEY_SIZE), secureRandom);
        KeyPair keyPair = keyPairGenerator.genKeyPair();
        // 获取公钥并写出
        byte[] publicKeyBytes = keyPair.getPublic().getEncoded();
        publicKeyBytes = Base64.getEncoder().encode(publicKeyBytes);
        writeFile(publicKeyFileName, publicKeyBytes);
        // 获取私钥并写出
        byte[] privateKeyBytes = keyPair.getPrivate().getEncoded();
        privateKeyBytes = Base64.getEncoder().encode(privateKeyBytes);
        writeFile(privateKeyFileName, privateKeyBytes);
    }

    private static void writeFile(String destPath, byte[] bytes) throws IOException {
        File dest = new File(destPath);
        if (!dest.exists()) {
            dest.createNewFile();
        }
        Files.write(dest.toPath(), bytes);
    }

    private static byte[] readFile(String filename) throws Exception {
        //读取文件地址返回字节码形式
        return Files.readAllBytes(new File(filename).toPath());
    }

}
