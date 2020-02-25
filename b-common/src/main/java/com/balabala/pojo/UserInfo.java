package com.balabala.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ProjectName: BALABALA
 * @Package: com.balabala.pojo
 * @ClassName: UserInfo
 * @Author: Administrator
 * @Description: ${description}
 * @Date: 2020/2/7 16:20
 * @Version: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {

    private Long id;

    private String username;

    private String role;

    private String headportrait;

}
