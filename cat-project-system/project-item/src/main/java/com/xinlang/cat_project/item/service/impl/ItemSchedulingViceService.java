package com.xinlang.cat_project.item.service.impl;

import com.xinlang.cat_project.item.mapper.ItemSchedulingMapper;
import com.xinlang.cat_project.item.mapper.ItemSchedulingViceMapper;
import com.xinlang.cat_project.item.pojo.ItemScheduling;
import com.xinlang.cat_project.item.pojo.ItemSchedulingVice;
import com.xinlang.cat_project.item.service.IItemSchedulingService;
import com.xinlang.cat_project.item.service.IItemSchedulingViceService;
import com.xinlang.zly_xyx.cat_common.service.impl.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ItemSchedulingViceService extends BaseService<ItemSchedulingVice> implements IItemSchedulingViceService {
    @Autowired
    private ItemSchedulingViceMapper itemSchedulingMapper;

    @Override
    public void saveSchedulings(List<ItemSchedulingVice> itemSchedulings) {
        int count = itemSchedulingMapper.insertList(itemSchedulings);
    }

    @Override
    public void deleteByAttribute(ItemSchedulingVice itemScheduling) {
        int i = itemSchedulingMapper.delete(itemScheduling);
    }
}
