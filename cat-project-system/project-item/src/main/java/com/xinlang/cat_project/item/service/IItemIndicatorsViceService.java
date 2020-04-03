package com.xinlang.cat_project.item.service;

import com.xinlang.cat_project.item.pojo.ItemIndicators;
import com.xinlang.cat_project.item.pojo.ItemIndicatorsVice;
import com.xinlang.zly_xyx.cat_common.service.IBaseService;

import java.util.List;

public interface IItemIndicatorsViceService extends IBaseService<ItemIndicatorsVice> {
    void saveIndicators(List<ItemIndicatorsVice> itemIndicators);

    void deleteByAttribute(ItemIndicatorsVice indicator);
}
