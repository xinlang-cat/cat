package com.xinlang.cat_project.item.service;

import com.xinlang.cat_project.item.pojo.ItemContactWay;
import com.xinlang.zly_xyx.cat_common.service.IBaseService;

import java.util.List;

public interface IItemContactWayService extends IBaseService<ItemContactWay> {
    void saveContactWays(List<ItemContactWay> itemContactWays);
}
