package com.xinlang.cat_project.item.service;

import com.xinlang.cat_project.item.pojo.ItemContent;
import com.xinlang.zly_xyx.cat_common.service.IBaseService;

import java.util.List;

public interface IItemContentService extends IBaseService<ItemContent> {

    void saveContents(List<ItemContent> itemContents);
}
