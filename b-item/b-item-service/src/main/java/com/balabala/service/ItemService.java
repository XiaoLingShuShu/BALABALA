package com.balabala.service;

import com.balabala.entity.DetailDTO;
import com.balabala.entity.Item;
import com.balabala.mapper.ItemMapper;
import com.balabala.pojo.PageResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.io.File;
import java.util.Deque;
import java.util.List;

@Service
public class ItemService {

    @Autowired
    private ItemMapper itemMapper;

    /**
     * 查询会员购页面商品
     * @return
     */
    public PageResult<Item> queryItemList(Integer page) {
        //查询一页只显示8个
        PageHelper.startPage(page,8);
        List<Item> items = itemMapper.selectAll();
        PageInfo<Item> info = new PageInfo<>(items);
        return new PageResult<>(info.getTotal(),info.getPages(),items);
    }

    /**
     * 查询会员购详情
     * @return
     */
    public DetailDTO queryItemDetail(Long id) {
        Item item = new Item();
        item.setId(id);
        //查询标准数据
        Item data = itemMapper.selectOne(item);
        DetailDTO detail = new DetailDTO();
        detail.setItem(data);
        //查询特殊数据（图片地址）
        String path = "G:\\idea项目文件夹\\bilibili-web\\images\\Detail\\"+data.getId();
        File file = new File(path);
        //获取当前文件夹下的文件名级路径
        File[] files = file.listFiles();
        //保存到返回结果
        detail.setFiles(files);
        return detail;
    }

    /**
     * 随机推荐会员购
     * @return
     */
    public List<Item> recommend() {
        return itemMapper.recommend();
    }

    /**
     * 添加想去次数
     * @return count
     */
    public void increaseWantGoCount(Long id) {
        Item item = new Item();
        //设置id
        item.setId(id);
        //先根据ID查询出原数据
        Item data = itemMapper.selectOne(item);
        //次数+1
        int wantGoCount = data.getWantGo() + 1;
        item.setWantGo(wantGoCount);
        //更新数据库
        itemMapper.updateByPrimaryKeySelective(item);
    }
}
