package com.balabala.user.client;

import com.balabala.entity.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @ProjectName: BALABALA
 * @Package: com.balabala.user.client
 * @ClassName: UserClient
 * @Author: Administrator
 * @Description: ${description}
 * @Date: 2020/2/7 17:06
 * @Version: 1.0
 */
@FeignClient("b-user-service")
public interface UserClient {

    /**
     * 根据用户名和密码查询用户
     * @param username 用户名
     * @param password 密码
     * @return 用户信息
     */
    @GetMapping("query")
    UserDTO queryUserByUsernameAndPassword(@RequestParam("username") String username, @RequestParam("password") String password);

}
