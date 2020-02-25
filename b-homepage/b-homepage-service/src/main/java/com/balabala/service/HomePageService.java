package com.balabala.service;

import com.balabala.DTO.VideoDTO;
import com.balabala.entity.VideoEntity;
import com.balabala.mapper.HomePageMapper;
import com.balabala.utils.BeanHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class HomePageService {

    @Autowired
    HomePageMapper homePageMapper;

    /**
     * 查询日推
     * @return
     */
    public List<VideoDTO> queryDailyPush() {
        List<VideoEntity> list = homePageMapper.queryDailyPush();
        return BeanHelper.copyWithCollection(list, VideoDTO.class);
    }

    /**
     * 查询动画分区动态
     * @return
     */
    public List<VideoDTO> queryAnimationDynamic() {
        //查询videoCategoryID为1的视频
        VideoEntity video = new VideoEntity();
        video.setVideoCategory("1");
        List<VideoEntity> list = homePageMapper.select(video);
        //随机排序
        Collections.shuffle(list);
        //只要前八个
        List<VideoEntity> newList = list.subList(0, 8);
         return BeanHelper.copyWithCollection(newList, VideoDTO.class);
    }

    /**
     * 查询MAD分区动态
     * @return
     */
    public List<VideoDTO> queryGameDynamic() {
        //查询videoCategoryID为2的视频
        VideoEntity video = new VideoEntity();
        video.setVideoCategory("2");
        List<VideoEntity> list = homePageMapper.select(video);
        //随机排序
        Collections.shuffle(list);
        //只要前八个
        List<VideoEntity> newList = list.subList(0, 8);
         return BeanHelper.copyWithCollection(newList, VideoDTO.class);
    }

    /**
     * 查询动漫分区动态
     * @return
     */
    public List<VideoDTO> querykichikuDynamic() {
        //查询videoCategoryID为3的视频
        VideoEntity video = new VideoEntity();
        video.setVideoCategory("3");
        List<VideoEntity> list = homePageMapper.select(video);
        //随机排序
        Collections.shuffle(list);
        //只要前八个
        List<VideoEntity> newList = list.subList(0, 8);
        return BeanHelper.copyWithCollection(newList, VideoDTO.class);
    }


    /**
     * 查询各分区排行
     * @return
     * @param categoryId
     */
    public List<VideoDTO> queryRanking(int categoryId) {
        List<VideoEntity> list = homePageMapper.selectRanking(categoryId);
        return BeanHelper.copyWithCollection(list,VideoDTO.class);
    }

}
