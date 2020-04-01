package com.xinlang.cat_project.item.service.impl;

import com.xinlang.cat_project.item.mapper.ItemContactWayMapper;
import com.xinlang.cat_project.item.mapper.ItemContactWayViceMapper;
import com.xinlang.cat_project.item.pojo.ItemContactWay;
import com.xinlang.cat_project.item.pojo.ItemContactWayVice;
import com.xinlang.cat_project.item.service.IItemContactWayService;
import com.xinlang.cat_project.item.service.IItemContactWayViceService;
import com.xinlang.zly_xyx.cat_common.service.impl.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ItemContactWayViceService extends BaseService<ItemContactWayVice> implements IItemContactWayViceService {
    @Autowired
    private ItemContactWayViceMapper itemContactWayMapper;

    @Override
    public void saveContactWays(List<ItemContactWayVice> itemContactWays) {
        int i = itemContactWayMapper.insertList(itemContactWays);
    }

    @Override
    public void deleteByAttribute(ItemContactWayVice itemContactWay) {
        int i = itemContactWayMapper.delete(itemContactWay);
    }
}
