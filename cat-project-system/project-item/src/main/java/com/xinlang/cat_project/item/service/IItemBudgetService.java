package com.xinlang.cat_project.item.service;

import com.xinlang.cat_project.item.pojo.ItemBudget;

import java.util.List;

public interface IItemBudgetService {
    void saveBudget(ItemBudget itemBudget);

    ItemBudget queryBudgetById(Integer id);

    List<ItemBudget> queryBudgetByFid(Integer Fid);

    List<ItemBudget> queryBudgetByIid(Integer iid);

    void updateBudget(ItemBudget itemBudget);

    void deleteBudget(Integer id);
}
