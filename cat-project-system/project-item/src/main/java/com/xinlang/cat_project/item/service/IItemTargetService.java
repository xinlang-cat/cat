package com.xinlang.cat_project.item.service;

import com.xinlang.cat_project.item.pojo.ItemTarget;
import com.xinlang.zly_xyx.cat_common.service.IBaseService;

import java.util.List;

public interface IItemTargetService extends IBaseService<ItemTarget> {

    void saveTargets(List<ItemTarget> itemTargets);
}
