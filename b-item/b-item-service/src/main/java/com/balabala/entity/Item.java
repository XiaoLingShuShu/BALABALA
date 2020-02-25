package com.balabala.entity;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "item")
public class Item {

    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;
    private String title;
    private String time;
    private String cityName;
    private String address;
    private Double price;
    private Boolean promo;
    private Integer wantGo;

}