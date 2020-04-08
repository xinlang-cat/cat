package com.xinlang.cat_project.item.service;

import com.xinlang.cat_project.item.pojo.ItemContactWay;
import com.xinlang.cat_project.item.pojo.ItemContactWayVice;
import com.xinlang.zly_xyx.cat_common.service.IBaseService;

import java.util.List;

public interface IItemContactWayViceService extends IBaseService<ItemContactWayVice> {
    void saveContactWays(List<ItemContactWayVice> itemContactWays);

    void deleteByAttribute(ItemContactWayVice itemContactWay);
}
