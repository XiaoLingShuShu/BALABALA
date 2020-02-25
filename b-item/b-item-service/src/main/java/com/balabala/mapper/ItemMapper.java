package com.balabala.mapper;

import com.balabala.entity.Item;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ItemMapper extends Mapper<Item> {

    /**
     * 随机推荐会员购
     * @return
     */
    List<Item> recommend();

}
