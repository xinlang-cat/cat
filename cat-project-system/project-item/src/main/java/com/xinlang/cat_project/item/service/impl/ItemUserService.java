package com.xinlang.cat_project.item.service.impl;

import com.xinlang.cat_project.item.VO.ItemUserInfo;
import com.xinlang.cat_project.item.enums.ExceptionEnum;
import com.xinlang.cat_project.item.exception.ItemException;
import com.xinlang.cat_project.item.mapper.ItemUserMapper;
import com.xinlang.cat_project.item.pojo.ItemUser;
import com.xinlang.cat_project.item.service.IItemUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemUserService implements IItemUserService {

    @Autowired
    private ItemUserMapper itemUserMapper;

    @Override
    public void saveItemUser(ItemUser itemUser) {
        int count = itemUserMapper.insertSelective(itemUser);
        if(count != 1){
            throw new ItemException(ExceptionEnum.ITEM_USER_SAVE_ERROR);
        }
    }

    @Override
    public ItemUserInfo queryItemUserInfoById(Integer id) {
        return null;
    }

    @Override
    public void updateItemUser(ItemUser itemUser) {
        int i = itemUserMapper.updateByPrimaryKeySelective(itemUser);
        if(i != 1){
            throw new ItemException(ExceptionEnum.ITEM_USER_UPDATE_ERROR);
        }
    }

    @Override
    public void deleteItemUser(Integer id) {
        int i = itemUserMapper.deleteByPrimaryKey(id);
        if(i != 1){
            throw new ItemException(ExceptionEnum.ITEM_USER_DELETE_ERROR);
        }
    }
}
