package com.xinlang.cat_project.item.service;

import com.xinlang.bean.projectInfo.ItemIndicators;
import com.xinlang.zly_xyx.cat_common.service.IBaseService;

import java.util.ArrayList;
import java.util.List;

public interface IItemIndicatorsService extends IBaseService<ItemIndicators> {
    void saveIndicators(List<ItemIndicators> itemIndicators);

    void deleteByAttribute(ItemIndicators indicator);

    List<ItemIndicators> getByIds(ArrayList<Integer> ids);
}
