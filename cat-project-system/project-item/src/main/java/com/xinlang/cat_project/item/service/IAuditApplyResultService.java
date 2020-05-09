package com.xinlang.cat_project.item.service;

import com.xinlang.bean.util.PageResult;
import com.xinlang.cat_project.item.pojo.auditApply;
import com.xinlang.cat_project.item.pojo.auditApplyResult;
import com.xinlang.zly_xyx.cat_common.service.IBaseService;

import java.util.List;
import java.util.Map;

public interface IAuditApplyResultService extends IBaseService<auditApplyResult> {


    void add(auditApplyResult auditApplyResult);

    List<auditApplyResult> findApplyList(Map<String, Object> params, Class<auditApplyResult> auditApplyResultClass);
    /**
     * 分页查询项目数据
     * @param page
     * @param rows
     * @param sortBy
     * @param desc
     * @param params
     * @return
     */
    PageResult<auditApplyResult> queryList(Integer page, Integer rows, String sortBy, Boolean desc, Map<String, Object> params);

    /**
     * 查询专家未审核的条数
     * @param auditApply_id
     * @return
     */
    Integer finUnChecked(Integer auditApply_id);
}
