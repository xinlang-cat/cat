package com.xinlang.cat_project.item.service;

import com.xinlang.cat_project.item.pojo.ItemFundBudget;
import com.xinlang.cat_project.item.pojo.ItemFundBudgetVice;
import com.xinlang.zly_xyx.cat_common.service.IBaseService;

import java.util.List;

public interface IItemFundBudgetViceService extends IBaseService<ItemFundBudgetVice> {
    void saveFundBudgets(List<ItemFundBudgetVice> itemFundBudgets);

    void deleteByAttribute(ItemFundBudgetVice itemFundBudget);
}
