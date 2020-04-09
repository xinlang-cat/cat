package com.xinlang.cat_project.item.service.impl;

import com.xinlang.cat_project.item.mapper.ItemIndicatorsViceMapper;
import com.xinlang.cat_project.item.pojo.ItemIndicatorsVice;
import com.xinlang.cat_project.item.service.IItemIndicatorsViceService;
import com.xinlang.zly_xyx.cat_common.service.impl.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ItemIndicatorsViceService extends BaseService<ItemIndicatorsVice> implements IItemIndicatorsViceService {
    @Autowired
    private ItemIndicatorsViceMapper itemIndicatorsMapper;

    @Override
    public void saveIndicators(List<ItemIndicatorsVice> itemIndicators) {
        int count = itemIndicatorsMapper.insertList(itemIndicators);
    }

    @Override
    public void deleteByAttribute(ItemIndicatorsVice indicator) {
        int i = itemIndicatorsMapper.delete(indicator);
    }
}
