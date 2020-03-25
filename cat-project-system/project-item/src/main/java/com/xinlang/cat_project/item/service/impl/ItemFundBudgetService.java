package com.xinlang.cat_project.item.service.impl;

import com.xinlang.cat_project.item.mapper.ItemFundBudgetMapper;
import com.xinlang.cat_project.item.pojo.ItemFundBudget;
import com.xinlang.cat_project.item.service.IItemFundBudgetService;
import com.xinlang.zly_xyx.cat_common.service.impl.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ItemFundBudgetService extends BaseService<ItemFundBudget> implements IItemFundBudgetService {
    @Autowired
    private ItemFundBudgetMapper itemFundBudgetMapper;

    @Override
    public void saveFundBudgets(List<ItemFundBudget> itemFundBudgets) {
        int i = itemFundBudgetMapper.insertList(itemFundBudgets);
    }
}
