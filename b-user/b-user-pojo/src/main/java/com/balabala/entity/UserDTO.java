package com.balabala.entity;

import lombok.Data;

/**
 * @ProjectName: BALABALA
 * @Package: com.balabala.entity
 * @ClassName: UserDTO
 * @Author: Administrator
 * @Description: ${description}
 * @Date: 2020/2/7 17:09
 * @Version: 1.0
 */
@Data
public class UserDTO {

    private Long id;
    private String userName;
    private String passWord;
    private String phone;
    private String headPortrait;

}
