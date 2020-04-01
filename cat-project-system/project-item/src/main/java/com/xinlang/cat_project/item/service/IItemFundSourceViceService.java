package com.xinlang.cat_project.item.service;

import com.xinlang.cat_project.item.pojo.ItemFundSource;
import com.xinlang.cat_project.item.pojo.ItemFundSourceVice;
import com.xinlang.zly_xyx.cat_common.service.IBaseService;

public interface IItemFundSourceViceService extends IBaseService<ItemFundSourceVice> {
    void deleteByAttribute(ItemFundSourceVice itemFundSource);
}
