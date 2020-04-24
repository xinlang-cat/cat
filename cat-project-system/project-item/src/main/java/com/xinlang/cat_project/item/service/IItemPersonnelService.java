package com.xinlang.cat_project.item.service;

import com.xinlang.bean.projectInfo.ItemPersonnel;
import com.xinlang.zly_xyx.cat_common.service.IBaseService;

import java.util.List;

public interface IItemPersonnelService extends IBaseService<ItemPersonnel> {

    void savePersonnels(List<ItemPersonnel> itemPersonnels);

    void deleteByAttribute(ItemPersonnel itemPersonnel);

    List<Integer> findItemIdsByuserId(Integer userId);
}
