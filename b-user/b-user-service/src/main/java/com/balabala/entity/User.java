package com.balabala.entity;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "user")
public class User {

    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;
    private String userName;
    private String passWord;
    private String phone;
    private String headPortrait;
}
