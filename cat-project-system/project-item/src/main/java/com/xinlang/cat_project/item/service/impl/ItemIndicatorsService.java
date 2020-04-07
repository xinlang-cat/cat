package com.xinlang.cat_project.item.service.impl;

import com.xinlang.cat_project.item.mapper.ItemIndicatorsMapper;
import com.xinlang.cat_project.item.pojo.ItemIndicators;
import com.xinlang.cat_project.item.service.IItemIndicatorsService;
import com.xinlang.zly_xyx.cat_common.service.impl.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ItemIndicatorsService extends BaseService<ItemIndicators> implements IItemIndicatorsService {
    @Autowired
    private ItemIndicatorsMapper itemIndicatorsMapper;

    @Override
    public void saveIndicators(List<ItemIndicators> itemIndicators) {
        int count = itemIndicatorsMapper.insertList(itemIndicators);
    }

    @Override
    public void deleteByAttribute(ItemIndicators indicator) {
        int i = itemIndicatorsMapper.delete(indicator);
    }

    @Override
    public List<ItemIndicators> getByIds(ArrayList<Integer> ids) {
        return itemIndicatorsMapper.getByIds(ids);
    }
}
