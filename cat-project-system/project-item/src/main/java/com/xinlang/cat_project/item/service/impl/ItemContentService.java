package com.xinlang.cat_project.item.service.impl;

import com.xinlang.cat_project.item.enums.ExceptionEnum;
import com.xinlang.cat_project.item.exception.ItemException;
import com.xinlang.cat_project.item.mapper.ItemContentMapper;
import com.xinlang.cat_project.item.pojo.ItemContent;
import com.xinlang.cat_project.item.service.IItemContentService;
;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
@Slf4j
public class ItemContentService implements IItemContentService {

    @Autowired
    private ItemContentMapper itemContentMapper;

    @Override
    public void savePlan(ItemContent itemContent) {

        //添加
        int count = itemContentMapper.insertSelective(itemContent);
        if (count != 1){
            throw new ItemException(ExceptionEnum.PLAN_SAVE_ERROR);
        }
    }

    @Override
    public ItemContent queryPlanById(Integer id) {
        ItemContent itemContent = itemContentMapper.selectByPrimaryKey(id);
        if(itemContent ==null){
            throw new ItemException(ExceptionEnum.DATA_NOT_FOUND);
        }
        return itemContent;
    }

    @Override
    public List<ItemContent> queryPlanByTId(Integer tid) {
        ItemContent itemContent = new ItemContent();
        itemContent.setItem_id(tid);
        List<ItemContent> list = itemContentMapper.select(itemContent);
        if(CollectionUtils.isEmpty(list)){
            throw new ItemException(ExceptionEnum.DATA_NOT_FOUND);
        }
        return list;
    }

    @Override
    public void updatePlan(ItemContent itemContent) {

        int i = itemContentMapper.updateByPrimaryKeySelective(itemContent);
        if(i != 1){
            throw new ItemException(ExceptionEnum.PLAN_UPDATE_ERROR);
        }
    }

    @Override
    public void deletePlan(Integer id) {
        int i = itemContentMapper.deleteByPrimaryKey(id);
        if(i != 1){
            throw new ItemException(ExceptionEnum.PLAN_DELETE_ERROR);
        }
    }
}
