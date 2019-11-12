package com.xinlang.cat_project.item.service.impl;

import com.xinlang.cat_project.item.enums.ExceptionEnum;
import com.xinlang.cat_project.item.exception.ItemException;
import com.xinlang.cat_project.item.mapper.ItemBudgetMapper;
import com.xinlang.cat_project.item.pojo.ItemBudget;
import com.xinlang.cat_project.item.service.IItemBudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class ItemBudgetService implements IItemBudgetService {

    @Autowired
    private ItemBudgetMapper itemBudgetMapper;

    @Override
    public void saveBudget(ItemBudget itemBudget) {
        int count = itemBudgetMapper.insertSelective(itemBudget);
        if (count != 1){
            throw new ItemException(ExceptionEnum.BUDGET_SAVE_ERROR);
        }
    }

    @Override
    public ItemBudget queryBudgetById(Integer id) {
        ItemBudget itemBudget = itemBudgetMapper.selectByPrimaryKey(id);
        if(itemBudget ==null){
            throw new ItemException(ExceptionEnum.DATA_NOT_FOUND);
        }
        return itemBudget;
    }

    @Override
    public List<ItemBudget> queryBudgetByFid(Integer Fid) {
        ItemBudget itemBudget = new ItemBudget();
        itemBudget.setFund_id(Fid);
        List<ItemBudget> list = itemBudgetMapper.select(itemBudget);
        if(CollectionUtils.isEmpty(list)){
            throw new ItemException(ExceptionEnum.DATA_NOT_FOUND);
        }
        return list;
    }

    @Override
    public List<ItemBudget> queryBudgetByIid(Integer Iid) {
        ItemBudget itemBudget = new ItemBudget();
        itemBudget.setItem_id(Iid);
        List<ItemBudget> list = itemBudgetMapper.select(itemBudget);
        if(CollectionUtils.isEmpty(list)){
            throw new ItemException(ExceptionEnum.DATA_NOT_FOUND);
        }
        return list;
    }

    @Override
    public void updateBudget(ItemBudget itemBudget) {
        int i = itemBudgetMapper.updateByPrimaryKeySelective(itemBudget);
        if(i != 1){
            throw new ItemException(ExceptionEnum.BUDGET_UPDATE_ERROR);
        }
    }

    @Override
    public void deleteBudget(Integer id) {
        int i = itemBudgetMapper.deleteByPrimaryKey(id);
        if(i != 1){
            throw new ItemException(ExceptionEnum.BUDGET_DELETE_ERROR);
        }
    }
}
