package com.xinlang.cat_project.item.service;

import com.xinlang.cat_project.item.pojo.ItemFund;
import com.xinlang.zly_xyx.cat_common.service.IBaseService;

import java.util.List;

public interface IItemFundService extends IBaseService<ItemFund> {

    void saveItemFunds(List<ItemFund> itemFunds);
}
