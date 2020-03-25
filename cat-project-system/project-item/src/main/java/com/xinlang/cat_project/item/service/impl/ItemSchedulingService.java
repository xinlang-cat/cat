package com.xinlang.cat_project.item.service.impl;

import com.xinlang.cat_project.item.mapper.ItemSchedulingMapper;
import com.xinlang.cat_project.item.pojo.ItemScheduling;
import com.xinlang.cat_project.item.service.IItemSchedulingService;
import com.xinlang.zly_xyx.cat_common.service.impl.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ItemSchedulingService extends BaseService<ItemScheduling> implements IItemSchedulingService {
    @Autowired
    private ItemSchedulingMapper itemSchedulingMapper;

    @Override
    public void saveSchedulings(List<ItemScheduling> itemSchedulings) {
        int count = itemSchedulingMapper.insertList(itemSchedulings);
    }

    @Override
    public void deleteByAttribute(ItemScheduling itemScheduling) {
        int i = itemSchedulingMapper.delete(itemScheduling);
    }
}
