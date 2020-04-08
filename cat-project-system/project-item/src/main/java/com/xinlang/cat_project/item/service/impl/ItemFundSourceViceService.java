package com.xinlang.cat_project.item.service.impl;

import com.xinlang.cat_project.item.mapper.ItemFundSourceMapper;
import com.xinlang.cat_project.item.mapper.ItemFundSourceViceMapper;
import com.xinlang.cat_project.item.pojo.ItemFundSource;
import com.xinlang.cat_project.item.pojo.ItemFundSourceVice;
import com.xinlang.cat_project.item.service.IItemFundSourceService;
import com.xinlang.cat_project.item.service.IItemFundSourceViceService;
import com.xinlang.zly_xyx.cat_common.service.impl.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ItemFundSourceViceService extends BaseService<ItemFundSourceVice> implements IItemFundSourceViceService {
    @Autowired
    private ItemFundSourceViceMapper itemFundSourceMapper;

    @Override
    public void deleteByAttribute(ItemFundSourceVice itemFundSource) {
        int i = itemFundSourceMapper.delete(itemFundSource);
    }
}
