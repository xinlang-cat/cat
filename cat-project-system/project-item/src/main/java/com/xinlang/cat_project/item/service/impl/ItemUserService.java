package com.xinlang.cat_project.item.service.impl;

import com.xinlang.bean.project_user.ProjectUser;
import com.xinlang.cat_project.item.enums.ExceptionEnum;
import com.xinlang.cat_project.item.exception.ItemException;
import com.xinlang.cat_project.item.fegin.ConsumeProjectUser;
import com.xinlang.cat_project.item.mapper.ItemUserMapper;
import com.xinlang.cat_project.item.pojo.ItemUser;
import com.xinlang.cat_project.item.service.IItemUserService;
import com.xinlang.zly_xyx.cat_common.service.impl.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ItemUserService extends BaseService<ItemUser> implements IItemUserService {

    @Autowired
    private ItemUserMapper itemUserMapper;
    @Autowired
    private ConsumeProjectUser consumeProjectUser;

    @Override
    @Transactional
    public void saveitemUsers(List<ItemUser> itemUsers) {
        itemUserMapper.insertList(itemUsers);
        /*for (ItemUser itemUser : itemUsers) {
            List<Integer> targetIds = itemUser.getTargetIds();
            for (Integer targetId : targetIds) {
                itemUserMapper.insertTargetUser(itemUser.getItem_id(),targetId,itemUser.getUser_id());
            }
        }*/
    }

    @Override
    public void insertTargetUser(Integer item_id, Integer targetId, Integer user_id) {
        itemUserMapper.insertTargetUser(item_id,targetId,user_id);
    }

    @Override
    public List<Integer> selectTargetUserByUserId(Integer item_id, Integer user_id) {
        return itemUserMapper.selectTargetUserByUserId(item_id, user_id);
    }

    @Override
    @Transactional
    public void DeleteTargetUser(Integer id) {
        ItemUser itemUser = itemUserMapper.selectByPrimaryKey(id);
        itemUserMapper.DeleteTargetUserByUserId(itemUser.getItem_id(),itemUser.getUser_id());
    }

    @Override
    @Transactional
    public void deleteItemUserByItemId(Integer item_id) {
        ItemUser itemUser = new ItemUser();
        itemUser.setItem_id(item_id);
        itemUserMapper.delete(itemUser);
        itemUserMapper.DeleteTargetUserByItemId(item_id);
    }
}
