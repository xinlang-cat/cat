package com.xinlang.cat_project.item.service;

import com.xinlang.cat_project.item.pojo.ItemBasic;
import com.xinlang.cat_project.item.pojo.PageResult;

import java.util.Map;

/**
 * 梁应昌
 * 2019/9/20
 */
public interface IItemBasicService {

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

    /**
     * 添加一条项目数据
     * @param basic
     */
    void saveItem(ItemBasic basic);

    /**
     * 查询单条项目数据
     * @param id
     * @return
     */
    ItemBasic queryItemById(Integer id);

    /**
     * 更改项目数据
     * @param basic
     */
    void updateItem(ItemBasic basic);

    /**
     * 删除
     * @param id
     */
    void deleteItem(Integer id);

    void discardItem(Integer id);
}
