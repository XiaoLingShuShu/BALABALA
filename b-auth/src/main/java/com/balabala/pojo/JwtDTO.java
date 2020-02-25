package com.balabala.pojo;

import com.balabala.config.JwtProperties;
import lombok.Data;

/**
 * @ProjectName: BALABALA
 * @Package: com.balabala.pojo
 * @ClassName: JwtDTO
 * @Author: Administrator
 * @Description: ${description}
 * @Date: 2020/2/8 16:34
 * @Version: 1.0
 */
@Data
public class JwtDTO {
    /**
     * 存放token的cookie名称
     */
    private String cookieName;
    /**
     * 存放token的cookie的domain
     */
    private String cookieDomain;
    /**
     * Token数据
     */
    private String token;

}
