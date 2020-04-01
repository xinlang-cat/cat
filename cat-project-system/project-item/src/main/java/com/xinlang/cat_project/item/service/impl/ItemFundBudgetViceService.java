package com.xinlang.cat_project.item.service.impl;

import com.xinlang.cat_project.item.mapper.ItemFundBudgetMapper;
import com.xinlang.cat_project.item.mapper.ItemFundBudgetViceMapper;
import com.xinlang.cat_project.item.pojo.ItemFundBudget;
import com.xinlang.cat_project.item.pojo.ItemFundBudgetVice;
import com.xinlang.cat_project.item.service.IItemFundBudgetService;
import com.xinlang.cat_project.item.service.IItemFundBudgetViceService;
import com.xinlang.zly_xyx.cat_common.service.impl.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ItemFundBudgetViceService extends BaseService<ItemFundBudgetVice> implements IItemFundBudgetViceService {
    @Autowired
    private ItemFundBudgetViceMapper itemFundBudgetMapper;

    @Override
    public void saveFundBudgets(List<ItemFundBudgetVice> itemFundBudgets) {
        int i = itemFundBudgetMapper.insertList(itemFundBudgets);
    }

    @Override
    public void deleteByAttribute(ItemFundBudgetVice itemFundBudget) {
        int i = itemFundBudgetMapper.delete(itemFundBudget);
    }
}
