package com.balabala.pojo;

import lombok.Data;

/**
 * @ProjectName: BALABALA
 * @Package: com.balabala.pojo
 * @ClassName: UserAndJwt
 * @Author: Administrator
 * @Description: ${description}
 * @Date: 2020/2/9 13:49
 * @Version: 1.0
 */
@Data
public class UserAndJwt {

    private JwtDTO jwt;

    private UserInfo userInfo;

}
