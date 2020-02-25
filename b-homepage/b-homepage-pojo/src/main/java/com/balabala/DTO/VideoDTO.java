package com.balabala.DTO;

import lombok.Data;

@Data
public class VideoDTO {
    //视频ID
    private String videoId;
    //视频名字
    private String videoName;
    //视频图片
    private String videoImage;
    //视频地址
    private String videoAddress;
    //视频观看数
    private String videoLookCount;
    //视频长短
    private String videoTime;
    //UP名
    private String upName;

}
