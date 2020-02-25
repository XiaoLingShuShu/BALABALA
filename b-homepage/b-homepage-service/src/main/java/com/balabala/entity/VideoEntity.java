package com.balabala.entity;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "video")
public class VideoEntity {

    @Id
    @KeySql(useGeneratedKeys = true)
    private String videoId; //视频ID
    private String videoName;//视频名字
    private String videoImage;//视频图片
    private String videoAddress;//视频地址
    private String videoLookCount;//视频观看数
    private String videoTime;//视频长短
    private String videoCategory;//视频类别
    private String upName;//UP名

}
