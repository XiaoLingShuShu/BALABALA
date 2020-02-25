package com.balabala.web;

import com.balabala.entity.User;
import com.balabala.entity.UserDTO;
import com.balabala.service.UserService;
import org.omg.CORBA.DynAnyPackage.Invalid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 用户注册
     * @param user
     * @return
     */
    @PostMapping("Register")
    public ResponseEntity<Boolean> queryRegisterUser(User user) {
        return ResponseEntity.ok(userService.registerUser(user));
    }

    /**
     * 根据用户名和密码查询用户
     * @param username 用户名
     * @param password 密码
     * @return 用户信息
     */
    @GetMapping("query")
    public UserDTO queryUserByUsernameAndPassword(@RequestParam("username") String username, @RequestParam("password") String password){
        return userService.queryUserByUsernameAndPassword(username,password);
    }

    /**
     * 用户找回密码
     * @return
     */
    @PostMapping("Reset")
    public ResponseEntity<Boolean> userResetPassWord(User user) {
        return ResponseEntity.ok(userService.reset(user));
    }

}
