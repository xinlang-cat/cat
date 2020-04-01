package com.xinlang.cat_project.item.service.impl;

import com.xinlang.cat_project.item.mapper.ItemFundDetailMapper;
import com.xinlang.cat_project.item.mapper.ItemFundDetailViceMapper;
import com.xinlang.cat_project.item.pojo.ItemFundDetail;
import com.xinlang.cat_project.item.pojo.ItemFundDetailVice;
import com.xinlang.cat_project.item.service.IItemFundDetailService;
import com.xinlang.cat_project.item.service.IItemFundDetailViceService;
import com.xinlang.zly_xyx.cat_common.service.impl.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ItemFundDetailViceService extends BaseService<ItemFundDetailVice> implements IItemFundDetailViceService {
    @Autowired
    private ItemFundDetailViceMapper itemFundDetailMapper;
}
