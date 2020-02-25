package com.balabala.filters;

import com.balabala.auth.Payload;
import com.balabala.config.FilterProperties;
import com.balabala.config.JwtProperties;
import com.balabala.pojo.UserInfo;
import com.balabala.utils.CookieUtils;
import com.balabala.utils.JwtUtils;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @ProjectName: BALABALA
 * @Package: com.balabala.filters
 * @ClassName: AuthFilter
 * @Author: Administrator
 * @Description: ${description}
 * @Date: 2020/2/9 20:56
 * @Version: 1.0
 */
@Slf4j
@Component
@EnableConfigurationProperties(JwtProperties.class)
public class AuthFilter extends ZuulFilter {

    @Autowired
    private JwtProperties prop;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private FilterProperties filterProp;

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return FilterConstants.FORM_BODY_WRAPPER_FILTER_ORDER - 1;
    }

    @Override
    public boolean shouldFilter() {
        // 获取上下文
        RequestContext ctx = RequestContext.getCurrentContext();
        // 获取request
        HttpServletRequest req = ctx.getRequest();
        // 获取路径
        String requestURI = req.getRequestURI();
        // 判断白名单
        return !isAllowPath(requestURI);
    }

    private boolean isAllowPath(String requestURI) {
        // 定义一个标记
        boolean flag = false;
        // 遍历允许访问的路径
        for (String path : this.filterProp.getAllowPaths()) {
            // 然后判断是否是符合
            if(requestURI.startsWith(path)){
                flag = true;
                break;
            }
        }
        return flag;
    }

    @Override
    public Object run() throws ZuulException {
        // 1.获取Request
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        // 2.获取用户的登录凭证jwt
        String token = CookieUtils.getCookieValue(request, prop.getUser().getCookieName());
        try {
            // 3.验证用户是否登录
            // 3.1.解析jwt，获取用户身份
            Payload<UserInfo> payload = JwtUtils.getInfoFromToken(token, prop.getPublicKey(), UserInfo.class);
            // 3.2.黑名单校验
            String tokenId = payload.getId();
            Boolean hasKey = redisTemplate.hasKey(tokenId);
            if (hasKey != null && hasKey) {
                // 在黑名单，token无效，抛出异常。
                throw new RuntimeException();
            }
            //  以上两个校验如果都通过才算登录有效

            // TODO 4.用户权限判断
            // 4.1.根据身份，查询用户权限信息
            UserInfo user = payload.getUserInfo();
            Long userId = user.getId();
            // 4.2.获取当前请求资源（微服务接口路径）
            String requestURI = request.getRequestURI();
            // 4.3.判断是否有访问资源的权限
            log.info("【网关】用户{}，角色{}，正在访问{}: {}",
                    user.getUsername(), user.getRole(), request.getMethod(), requestURI);
        } catch (Exception e) {
            // 拦截请求
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
        }
        return null;
    }



}
