package com.xinlang.cat_project.item.service;

import com.xinlang.cat_project.item.pojo.modifyApply;
import com.xinlang.zly_xyx.cat_common.service.IBaseService;

import java.util.List;
import java.util.Map;

public interface IModifyApplyService extends IBaseService<modifyApply> {


    void add(modifyApply modifyApply);

    List<modifyApply> findApplyList(Map<String, Object> params, Class<modifyApply> modifyApplyClass);
}
