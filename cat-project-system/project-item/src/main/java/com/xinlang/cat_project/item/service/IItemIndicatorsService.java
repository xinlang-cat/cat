package com.xinlang.cat_project.item.service;

import com.xinlang.cat_project.item.pojo.ItemIndicators;
import com.xinlang.zly_xyx.cat_common.service.IBaseService;

import java.util.List;

public interface IItemIndicatorsService extends IBaseService<ItemIndicators> {
    void saveIndicators(List<ItemIndicators> itemIndicators);
}
