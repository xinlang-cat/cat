package com.xinlang.cat_project.item.service;

import com.xinlang.cat_project.item.pojo.ItemPersonnel;
import com.xinlang.zly_xyx.cat_common.service.IBaseService;

import java.util.List;

public interface IItemPersonnelService extends IBaseService<ItemPersonnel> {

    void savePersonnels(List<ItemPersonnel> itemPersonnels);
}
