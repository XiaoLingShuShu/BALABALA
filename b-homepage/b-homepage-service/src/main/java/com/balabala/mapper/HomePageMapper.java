package com.balabala.mapper;

import com.balabala.entity.VideoEntity;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface HomePageMapper extends Mapper<VideoEntity> {

    /**
     * 查询日推
     * @return
     */
    List<VideoEntity> queryDailyPush();

    /**
     * 查询动画分区排行榜
     * @return
     * @param categoryId
     */
    List<VideoEntity> selectRanking(int categoryId);
}
