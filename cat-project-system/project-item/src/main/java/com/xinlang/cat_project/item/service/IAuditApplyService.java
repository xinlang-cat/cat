package com.xinlang.cat_project.item.service;

import com.xinlang.cat_project.item.pojo.auditApply;
import com.xinlang.zly_xyx.cat_common.service.IBaseService;

import java.util.List;
import java.util.Map;

public interface IAuditApplyService extends IBaseService<auditApply> {


    void add(auditApply auditApply);

    List<auditApply> findApplyList(Map<String, Object> params, Class<auditApply> auditApplyClass);
}
