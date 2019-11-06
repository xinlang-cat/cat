package com.xinlang.cat_project.item.service;

import com.xinlang.cat_project.item.pojo.ItemUser;

import java.util.List;
import java.util.Map;

public interface IItemUserService {
    void saveItemUser(ItemUser itemUser);

    Map<String,Object> queryItemUserInfoById(Integer id);

    List<Map<String, Object>> queryItemUserInfoAllByIid(Integer Iid);

    void updateItemUser(ItemUser itemUser);

    void deleteItemUser(Integer id);
}
