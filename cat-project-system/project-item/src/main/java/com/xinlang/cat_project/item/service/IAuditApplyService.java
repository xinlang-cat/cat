package com.xinlang.cat_project.item.service;

import com.xinlang.cat_project.item.pojo.PageResult;
import com.xinlang.cat_project.item.pojo.auditApply;
import com.xinlang.zly_xyx.cat_common.service.IBaseService;

import java.util.List;
import java.util.Map;

public interface IAuditApplyService extends IBaseService<auditApply> {


    void add(auditApply auditApply);

    List<auditApply> findApplyList(Map<String, Object> params, Class<auditApply> auditApplyClass);
    /**
     * 分页查询项目数据
     * @param page
     * @param rows
     * @param sortBy
     * @param desc
     * @param params
     * @return
     */
    PageResult<auditApply> queryList(Integer page, Integer rows, String sortBy, Boolean desc, Map<String, Object> params);
}
