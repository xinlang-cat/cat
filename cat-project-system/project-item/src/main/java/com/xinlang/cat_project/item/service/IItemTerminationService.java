package com.xinlang.cat_project.item.service;

import com.xinlang.bean.util.PageResult;
import com.xinlang.bean.projectInfo.ItemTermination;
import com.xinlang.zly_xyx.cat_common.service.IBaseService;

import java.util.Map;

public interface IItemTerminationService extends IBaseService<ItemTermination> {
    void deleteByAttribute(ItemTermination ItemTermination);
    PageResult<ItemTermination> queryList(Integer page, Integer rows, String sortBy, Boolean desc, Map<String, Object> params);
}
