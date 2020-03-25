package com.xinlang.cat_project.item.service;

import com.xinlang.cat_project.item.pojo.ItemFundBudget;
import com.xinlang.zly_xyx.cat_common.service.IBaseService;

import java.util.List;

public interface IItemFundBudgetService extends IBaseService<ItemFundBudget> {
    void saveFundBudgets(List<ItemFundBudget> itemFundBudgets);

    void deleteByAttribute(ItemFundBudget itemFundBudget);
}
