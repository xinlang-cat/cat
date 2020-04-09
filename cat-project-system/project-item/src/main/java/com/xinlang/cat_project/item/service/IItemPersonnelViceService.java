package com.xinlang.cat_project.item.service;

import com.xinlang.cat_project.item.pojo.ItemPersonnelVice;
import com.xinlang.zly_xyx.cat_common.service.IBaseService;

import java.util.List;

public interface IItemPersonnelViceService extends IBaseService<ItemPersonnelVice> {

    void savePersonnels(List<ItemPersonnelVice> itemPersonnels);

    void deleteByAttribute(ItemPersonnelVice itemPersonnel);
}
