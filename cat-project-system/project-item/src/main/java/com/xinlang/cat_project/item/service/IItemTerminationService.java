package com.xinlang.cat_project.item.service;

import com.xinlang.cat_project.item.pojo.ItemFundSource;
import com.xinlang.cat_project.item.pojo.ItemTermination;
import com.xinlang.zly_xyx.cat_common.service.IBaseService;

public interface IItemTerminationService extends IBaseService<ItemTermination> {
    void deleteByAttribute(ItemTermination ItemTermination);
}
