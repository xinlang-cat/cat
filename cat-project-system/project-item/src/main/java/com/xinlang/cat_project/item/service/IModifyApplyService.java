package com.xinlang.cat_project.item.service;

import com.xinlang.bean.util.PageResult;
import com.xinlang.cat_project.item.pojo.modifyApply;
import com.xinlang.zly_xyx.cat_common.service.IBaseService;

import java.util.List;
import java.util.Map;

public interface IModifyApplyService extends IBaseService<modifyApply> {


    List<modifyApply> findApplyList(Map<String, Object> params, Class<modifyApply> modifyApplyClass);
    /**
     * 分页查询项目数据
     * @param page
     * @param rows
     * @param sortBy
     * @param desc
     * @param params
     * @return
     */
    PageResult<modifyApply> queryList(Integer page, Integer rows, String sortBy, Boolean desc, Map<String, Object> params);
}
