package com.xinlang.cat_project.item.service.impl;

import com.xinlang.cat_project.item.mapper.ItemFundDetailMapper;
import com.xinlang.cat_project.item.pojo.ItemFundDetail;
import com.xinlang.cat_project.item.service.IItemFundDetailService;
import com.xinlang.zly_xyx.cat_common.service.impl.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ItemFundDetailService extends BaseService<ItemFundDetail> implements IItemFundDetailService {
    @Autowired
    private ItemFundDetailMapper itemFundDetailMapper;
}
