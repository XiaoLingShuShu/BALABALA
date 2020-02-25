package com.balabala.service;

import com.balabala.auth.Payload;
import com.balabala.config.JwtProperties;
import com.balabala.entity.UserDTO;
import com.balabala.pojo.JwtDTO;
import com.balabala.pojo.UserAndJwt;
import com.balabala.pojo.UserInfo;
import com.balabala.user.client.UserClient;
import com.balabala.utils.CookieUtils;
import com.balabala.utils.JwtUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @ProjectName: BALABALA
 * @Package: com.balabala.service
 * @ClassName: AuthService
 * @Author: Administrator
 * @Description: ${description}
 * @Date: 2020/2/7 20:22
 * @Version: 1.0
 */
@Service
public class AuthService {

    private static final String USER_ROLE = "role_user";
    @Autowired
    private JwtProperties prop;
    @Autowired
    private UserClient userClient;
    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 登录授权
     * @param userName 用户名
     * @param passWord 密码
     * @return
     */
    public JwtDTO login(String userName, String passWord, HttpServletResponse response) {
        try {
            //查询用户,没查到会抛出异常
            UserDTO user = userClient.queryUserByUsernameAndPassword(userName, passWord);
            //生成userInfo
            UserInfo userInfo = new UserInfo(user.getId(), user.getUserName(), USER_ROLE, user.getHeadPortrait());
            //生成token
            String token = JwtUtils.generateTokenExpireInMinutes(userInfo, prop.getPrivateKey(), prop.getUser().getExpire());
            //封装返回数据
            JwtDTO jwt = new JwtDTO();
            jwt.setToken(token);
            jwt.setCookieDomain(prop.getUser().getCookieDomain());
            jwt.setCookieName(prop.getUser().getCookieName());
            return jwt;
        } catch (Exception e) {
            throw new RuntimeException("用户不存在！");
        }
    }

    /**
     * 验证用户token
     * @param request
     * @return
     */
    public UserAndJwt verifyUser(HttpServletRequest request) {
        try {
            //读取token
            String token = CookieUtils.getCookieValue(request, prop.getUser().getCookieName());
            //获取token信息
            Payload<UserInfo> payLoad = JwtUtils.getInfoFromToken(token, prop.getPublicKey(), UserInfo.class);
            //获取tokenID，校验是否在redis黑名单中
            Boolean hasKey = redisTemplate.hasKey(payLoad.getId());
            if (hasKey != null && hasKey) {
                throw new RuntimeException("TOKEN已过期!");
            }
            //获取过期时间
            Date expiration = payLoad.getExpiration();
            //获取刷新时间
            DateTime refreshTime = new DateTime(expiration.getTime()).minusMinutes(prop.getUser().getMinRefreshInterval());
            //创建返回结果对象
            UserAndJwt userAndJwt = new UserAndJwt();
            //判断是否已到刷新时间
            if (!refreshTime.isBefore(System.currentTimeMillis())) {
                //没过刷新时间，直接返回用户信息
                userAndJwt.setUserInfo(payLoad.getUserInfo());
                return userAndJwt;
            }
            //过了刷新时间，生成新的token
            token = JwtUtils.generateTokenExpireInMinutes(payLoad.getUserInfo(), prop.getPrivateKey(), prop.getUser().getExpire());
            //封装返回数据
            JwtDTO jwt = new JwtDTO();
            jwt.setToken(token);
            jwt.setCookieDomain(prop.getUser().getCookieDomain());
            jwt.setCookieName(prop.getUser().getCookieName());
            userAndJwt.setJwt(jwt);
            userAndJwt.setUserInfo(payLoad.getUserInfo());
            return userAndJwt;
        } catch (Exception e) {
            throw new RuntimeException("用户未登录或Token已过期!");
        }
    }

    /**
     * 注销登录
     * @param request
     * @param response
     */
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        //获取token
        String token = CookieUtils.getCookieValue(request, prop.getUser().getCookieName());
        //解析token
        Payload<UserInfo> payLoad = JwtUtils.getInfoFromToken(token, prop.getPublicKey(), UserInfo.class);
        //获取id和有效期时长
        String id = payLoad.getId();
        //到期时间减去当前时间
        long time = payLoad.getExpiration().getTime() - System.currentTimeMillis();
        //写入redis,剩余时间超过10秒才写
        if (time > 10000) {
            redisTemplate.opsForValue().set(id,"",time, TimeUnit.MILLISECONDS);
        }
        //删除cookie
        CookieUtils.deleteCookie(prop.getUser().getCookieName(),prop.getUser().getCookieDomain(),response);
    }
}
