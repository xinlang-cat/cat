package com.xinlang.cat_project.item.service.impl;

import com.xinlang.cat_project.item.enums.ExceptionEnum;
import com.xinlang.cat_project.item.exception.ItemException;
import com.xinlang.cat_project.item.mapper.ItemBudgetMapper;
import com.xinlang.cat_project.item.mapper.ItemFundMapper;
import com.xinlang.cat_project.item.pojo.ItemBudget;
import com.xinlang.cat_project.item.pojo.ItemFund;
import com.xinlang.cat_project.item.service.IItemFundService;
import com.xinlang.zly_xyx.cat_common.service.impl.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class ItemFundService extends BaseService<ItemFund> implements IItemFundService {

    @Autowired
    private ItemFundMapper itemFundMapper;
    @Autowired
    private ItemBudgetMapper itemBudgetMapper;

    @Override
    public void saveItemFunds(List<ItemFund> itemFunds) {
        itemFundMapper.insertList(itemFunds);
    }
}
