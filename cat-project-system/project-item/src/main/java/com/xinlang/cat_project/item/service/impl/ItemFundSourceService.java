package com.xinlang.cat_project.item.service.impl;

import com.xinlang.cat_project.item.mapper.ItemFundSourceMapper;
import com.xinlang.cat_project.item.pojo.ItemFundSource;
import com.xinlang.cat_project.item.service.IItemFundSourceService;
import com.xinlang.zly_xyx.cat_common.service.impl.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ItemFundSourceService extends BaseService<ItemFundSource> implements IItemFundSourceService {
    @Autowired
    private ItemFundSourceMapper itemFundSourceMapper;
}
