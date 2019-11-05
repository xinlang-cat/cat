package com.xinlang.cat_project.item.service.impl;

import com.xinlang.cat_project.item.enums.ExceptionEnum;
import com.xinlang.cat_project.item.exception.ItemException;
import com.xinlang.cat_project.item.mapper.ItemBudgetMapper;
import com.xinlang.cat_project.item.mapper.ItemFundMapper;
import com.xinlang.cat_project.item.pojo.ItemBudget;
import com.xinlang.cat_project.item.pojo.ItemFund;
import com.xinlang.cat_project.item.service.IItemFundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class ItemFundService implements IItemFundService {

    @Autowired
    private ItemFundMapper itemFundMapper;
    @Autowired
    private ItemBudgetMapper itemBudgetMapper;

    @Override
    public void saveFund(ItemFund itemFund) {
        //添加
        int count = itemFundMapper.insertSelective(itemFund);
        if (count != 1){
            throw new ItemException(ExceptionEnum.FUND_SAVE_ERROR);
        }
    }

    @Override
    public ItemFund queryFundById(Integer id) {
        ItemFund itemFund = itemFundMapper.selectByPrimaryKey(id);
        if(itemFund ==null){
            throw new ItemException(ExceptionEnum.DATA_NOT_FOUND);
        }
        return itemFund;
    }

    @Override
    public List<ItemFund> queryFundByIId(Integer Iid) {
        ItemFund itemFund = new ItemFund();

        itemFund.setItem_id(Iid);
        List<ItemFund> list = itemFundMapper.select(itemFund);
        if(CollectionUtils.isEmpty(list)){
            throw new ItemException(ExceptionEnum.DATA_NOT_FOUND);
        }
        return list;
    }

    @Override
    public void updateFund(ItemFund itemFund) {
        int i = itemFundMapper.updateByPrimaryKeySelective(itemFund);
        if(i != 1){
            throw new ItemException(ExceptionEnum.FUND_UPDATE_ERROR);
        }
    }

    @Override
    @Transactional
    public void deleteFund(Integer id) {
        int i = itemFundMapper.deleteByPrimaryKey(id);
        if(i != 1){
            throw new ItemException(ExceptionEnum.FUND_DELETE_ERROR);
        }
        //删除相关预算
        ItemBudget itemBudget = new ItemBudget();
        itemBudget.setFund_id(id);
        itemBudgetMapper.delete(itemBudget);
    }
}
