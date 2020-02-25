package com.balabala.mapper;

import com.balabala.entity.User;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface UserMapper extends Mapper<User> {

    /**
     * 重置密码
     * @param userName
     * @param phone
     * @param passWord
     * @return
     */
    Boolean reset(@Param("userName") String userName,@Param("phone") String phone,@Param("passWord") String passWord);

}
