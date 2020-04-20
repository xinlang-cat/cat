package com.xinlang.cat_project.item.service.impl;

import com.xinlang.cat_project.item.mapper.ItemFundSourceMapper;
import com.xinlang.cat_project.item.mapper.ItemTerminationMapper;
import com.xinlang.cat_project.item.pojo.ItemFundSource;
import com.xinlang.cat_project.item.pojo.ItemTermination;
import com.xinlang.cat_project.item.service.IItemFundSourceService;
import com.xinlang.cat_project.item.service.IItemTerminationService;
import com.xinlang.zly_xyx.cat_common.service.impl.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ItemTerminationService extends BaseService<ItemTermination> implements IItemTerminationService {
    @Autowired
    private ItemTerminationMapper itemTerminationMapper;

    @Override
    public void deleteByAttribute(ItemTermination itemTermination) {
        int i = itemTerminationMapper.delete(itemTermination);
    }
}
