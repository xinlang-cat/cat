package com.xinlang.cat_project.item.service;

import com.xinlang.cat_project.item.pojo.ItemContent;

import java.util.List;

public interface IItemContentService {
    void savePlan(ItemContent itemContent);

    ItemContent queryPlanById(Integer id);

    List<ItemContent> queryPlanByTId(Integer tid);

    void updatePlan(ItemContent itemContent);

    void deletePlan(Integer id);
}
