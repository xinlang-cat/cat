package com.xinlang.zly_xyx.cat_common.service;

import com.xinlang.zly_xyx.common.Page;

import java.util.List;
import java.util.Map;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019-12-03
 */
public interface IBaseService<T>{
    void save(T t);//添加
    void update(T t);//修改
    List<T> findListByParams(Map<String,Object> params,Class<T> beanClazz);//查询list
    Page<T> findPageByParams(Map<String,Object> params,Class<T> beanClazz);//查询page
    T findById(Integer id);//根据id查询
    void delete(Integer id);//删除
}
