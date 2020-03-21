package com.xinlang.cat_project.item.service.impl;

import com.xinlang.cat_project.item.mapper.ItemContactWayMapper;
import com.xinlang.cat_project.item.pojo.ItemContactWay;
import com.xinlang.cat_project.item.service.IItemContactWayService;
import com.xinlang.zly_xyx.cat_common.service.impl.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ItemContactWayService extends BaseService<ItemContactWay> implements IItemContactWayService {
    @Autowired
    private ItemContactWayMapper itemContactWayMapper;

    @Override
    public void saveContactWays(List<ItemContactWay> itemContactWays) {
        int i = itemContactWayMapper.insertList(itemContactWays);
    }
}
