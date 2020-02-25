package com.balabala.web;

import com.balabala.DTO.VideoDTO;
import com.balabala.service.HomePageService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HomePageController {

    @Autowired
    HomePageService service;

    /**
     * 查询日推
     * @return
     */
    @GetMapping("DailyPush")
    public ResponseEntity<List<VideoDTO>> queryDailyPush() {
        List<VideoDTO> list = service.queryDailyPush();
        return ResponseEntity.ok(list);
    }

    /**
     * 查询动画分区动态
     * @return
     */
    @GetMapping("AnimationDynamic")
    public ResponseEntity<List<VideoDTO>> queryAnimationDynamic() {
        List<VideoDTO> list = service.queryAnimationDynamic();
        return ResponseEntity.ok(list);
    }

    /**
     * 查询游戏分区动态
     * @return
     */
    @GetMapping("game")
    public ResponseEntity<List<VideoDTO>> queryGameDynamic() {
        List<VideoDTO> list = service.queryGameDynamic();
        return ResponseEntity.ok(list);
    }

    /**
     * 查询鬼畜分区动态
     * @return
     */
    @GetMapping("kichiku")
    public ResponseEntity<List<VideoDTO>> querykichikuDynamic() {
        List<VideoDTO> list = service.querykichikuDynamic();
        return ResponseEntity.ok(list);
    }

    /**
     * 查询各分区排行
     * @return
     */
    @GetMapping("Ranking")
    public ResponseEntity<List<VideoDTO>> queryRanking(@RequestParam("categoryId") int categoryId) {
        List<VideoDTO> list = service.queryRanking(categoryId);
        return ResponseEntity.ok(list);
    }


}
