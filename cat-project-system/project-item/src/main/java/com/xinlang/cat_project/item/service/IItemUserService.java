package com.xinlang.cat_project.item.service;

import com.xinlang.cat_project.item.VO.ItemUserInfo;
import com.xinlang.cat_project.item.pojo.ItemUser;

public interface IItemUserService {
    void saveItemUser(ItemUser itemUser);

    ItemUserInfo queryItemUserInfoById(Integer id);

    void updateItemUser(ItemUser itemUser);

    void deleteItemUser(Integer id);
}
