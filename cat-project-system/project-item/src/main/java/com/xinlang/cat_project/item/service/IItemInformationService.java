package com.xinlang.cat_project.item.service;

import com.xinlang.bean.projectInfo.ItemInformation;
import com.xinlang.bean.util.PageResult;
import com.xinlang.zly_xyx.cat_common.service.IBaseService;

import java.util.List;
import java.util.Map;

public interface IItemInformationService extends IBaseService<ItemInformation> {
    PageResult<ItemInformation> queryList(Integer page, Integer rows, String sortBy, Boolean desc, Map<String, Object> params);

    List<ItemInformation> queryMyItem();
}
