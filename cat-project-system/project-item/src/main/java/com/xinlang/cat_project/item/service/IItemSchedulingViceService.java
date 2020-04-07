package com.xinlang.cat_project.item.service;

import com.xinlang.cat_project.item.pojo.ItemScheduling;
import com.xinlang.cat_project.item.pojo.ItemSchedulingVice;
import com.xinlang.zly_xyx.cat_common.service.IBaseService;

import java.util.List;

public interface IItemSchedulingViceService extends IBaseService<ItemSchedulingVice> {
    void saveSchedulings(List<ItemSchedulingVice> itemSchedulings);

    void deleteByAttribute(ItemSchedulingVice itemScheduling);
}
