package com.balabala.web;

import com.balabala.entity.DetailDTO;
import com.balabala.entity.Item;
import com.balabala.pojo.PageResult;
import com.balabala.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ItemController {

    @Autowired
    private ItemService itemService;

    /**
     * 查询会员购页面商品列表
     * @return
     */
    @GetMapping("ItemList")
    public ResponseEntity<PageResult<Item>> queryItemList(@RequestParam(value = "page",defaultValue = "1") Integer page
    ) {
        return ResponseEntity.ok(itemService.queryItemList(page));
    }

    /**
     * 查询会员购详情
     * @return
     */
    @GetMapping("ItemDetail")
    public ResponseEntity<DetailDTO> queryItemDetail(@RequestParam(value = "id") Long id) {
        return ResponseEntity.ok(itemService.queryItemDetail(id));
    }

    /**
     * 随机推荐活动
     */
    @GetMapping("recommend")
    public ResponseEntity<List<Item>> recommend() {
        return ResponseEntity.ok(itemService.recommend());
    }

    /**
     * 添加想去次数
     * @return
     */
    @PutMapping("WantGo")
    public void increaseWantGoCount(@RequestParam("id") Long id){
        itemService.increaseWantGoCount(id);
        ResponseEntity.ok().build();
    }

}
