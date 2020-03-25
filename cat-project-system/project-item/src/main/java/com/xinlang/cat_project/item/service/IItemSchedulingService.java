package com.xinlang.cat_project.item.service;

import com.xinlang.cat_project.item.pojo.ItemScheduling;
import com.xinlang.zly_xyx.cat_common.service.IBaseService;

import java.util.List;

public interface IItemSchedulingService extends IBaseService<ItemScheduling> {
    void saveSchedulings(List<ItemScheduling> itemSchedulings);

    void deleteByAttribute(ItemScheduling itemScheduling);
}
