package com.xinlang.cat_project.item.service;

import com.xinlang.cat_project.item.pojo.ItemBasic;
import com.xinlang.cat_project.item.pojo.PageResult;
import com.xinlang.zly_xyx.cat_common.service.IBaseService;

import java.util.List;
import java.util.Map;

/**
 * 梁应昌
 * 2019/9/20
 */
public interface IItemBasicService extends IBaseService<ItemBasic> {

    /**
     * 分页查询项目数据
     * @param page
     * @param rows
     * @param sortBy
     * @param desc
     * @param params
     * @return
     */
    PageResult<ItemBasic> queryList(Integer page, Integer rows, String sortBy, Boolean desc, Map<String, Object> params);

    List<ItemBasic> queryCompanyItem();

    void discardItem(Integer id);

    List<ItemBasic> findByName(String name);
}
