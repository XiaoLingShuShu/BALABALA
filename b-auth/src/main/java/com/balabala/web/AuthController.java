package com.balabala.web;

import com.balabala.pojo.JwtDTO;
import com.balabala.pojo.UserAndJwt;
import com.balabala.pojo.UserInfo;
import com.balabala.service.AuthService;
import org.omg.CORBA.DynAnyPackage.Invalid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ProjectName: BALABALA
 * @Package: com.balabala.web
 * @ClassName: AuthController
 * @Author: Administrator
 * @Description: ${description}
 * @Date: 2020/2/7 20:17
 * @Version: 1.0
 */
@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    /**
     * 登录授权
     * @param username 用户名
     * @param password 密码
     * @return
     */
    @PostMapping("login")
    public ResponseEntity<JwtDTO> login(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            HttpServletResponse response
    ) {
        return ResponseEntity.ok(authService.login(username,password,response));
    }

    /**
     * 验证用户TOKEN
     * @param request
     * @return
     */
    @GetMapping("verify")
    public ResponseEntity<UserAndJwt> verifyUser(HttpServletRequest request) {
        return ResponseEntity.ok(authService.verifyUser(request));
    }

    /**
     * 注销登录
     * @param request
     * @param response
     * @return
     */
    @PostMapping("logout")
    public ResponseEntity<Void> logout(HttpServletRequest request,HttpServletResponse response) {
        authService.logout(request,response);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
