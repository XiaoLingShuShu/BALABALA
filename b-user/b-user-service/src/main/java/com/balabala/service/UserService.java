package com.balabala.service;

import com.balabala.entity.User;
import com.balabala.entity.UserDTO;
import com.balabala.mapper.UserMapper;
import com.balabala.utils.BeanHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 用户注册
     * @param user
     * @return
     */
    public Boolean registerUser(User user) {
        int count = userMapper.insert(user);
        return count == 1;
    }

    /**
     * 用户重制密码
     * @param user
     * @return
     */
    public Boolean reset(User user) {
        return userMapper.reset(user.getUserName(),user.getPhone(),user.getPassWord());
        //TODO 未做验证
    }

    /**
     * 根据用户名和密码查询用户
     * @param username 用户名
     * @param password 密码
     * @return 用户信息
     */
    public UserDTO queryUserByUsernameAndPassword(String username,String password) {
        //设置用户名和密码
        User user = new User();
        user.setUserName(username);
        user.setPassWord(password);
        //通用mapper会根据非空属性进行查询
        user = userMapper.selectOne(user);
        //类型转换之后返回
        return BeanHelper.copyProperties(user, UserDTO.class);
    }
}
