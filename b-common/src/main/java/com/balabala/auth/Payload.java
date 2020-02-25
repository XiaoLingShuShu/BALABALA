package com.balabala.auth;

import lombok.Data;

import java.util.Date;

/**
 * @ProjectName: BALABALA
 * @Package: com.balabala.auth
 * @ClassName: Payload
 * @Author: Administrator
 * @Description: ${description}
 * @Date: 2020/2/7 16:12
 * @Version: 1.0
 */
@Data
public class Payload<T> {
    private String id;
    private T userInfo;
    private Date expiration;
}
