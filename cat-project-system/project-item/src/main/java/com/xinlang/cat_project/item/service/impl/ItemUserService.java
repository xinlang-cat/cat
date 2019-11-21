package com.xinlang.cat_project.item.service.impl;

import com.xinlang.bean.project_user.ProjectUser;
import com.xinlang.cat_project.item.enums.ExceptionEnum;
import com.xinlang.cat_project.item.exception.ItemException;
import com.xinlang.cat_project.item.fegin.ConsumeProjectUser;
import com.xinlang.cat_project.item.mapper.ItemUserMapper;
import com.xinlang.cat_project.item.pojo.ItemUser;
import com.xinlang.cat_project.item.service.IItemUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ItemUserService implements IItemUserService {

    @Autowired
    private ItemUserMapper itemUserMapper;
    @Autowired
    private ConsumeProjectUser consumeProjectUser;

    @Override
    @Transactional
    public void saveItemUser(ItemUser itemUser) {
        int count = itemUserMapper.insertSelective(itemUser);
        if(count != 1){
            throw new ItemException(ExceptionEnum.ITEM_USER_SAVE_ERROR);
        }
        List<Integer> targetIds = itemUser.getTargetIds();
        if (!CollectionUtils.isEmpty(targetIds)){
            for (Integer targetId : targetIds) {
                itemUserMapper.insertTargetUser(itemUser.getItem_id(),targetId,itemUser.getUser_id());
            }
        }
    }

    @Override
    @Transactional
    public Map<String,Object> queryItemUserInfoById(Integer id) {

        Map<String, Object> ItemUserInfo = new HashMap<>();
        ItemUser itemUser = itemUserMapper.selectByPrimaryKey(id);
        List<Integer> list = itemUserMapper.selectTargetUserByUserId(itemUser.getItem_id(), itemUser.getUser_id());
        itemUser.setTargetIds(list);
        List<ProjectUser> projectUser = consumeProjectUser.findByUserId(itemUser.getUser_id());
        ItemUserInfo.put("itemUser",itemUser);
        ItemUserInfo.put("projectUser",projectUser.get(0));
        return ItemUserInfo;
    }

    @Override
    public List<Map<String, Object>> queryItemUserInfoAllByIid(Integer Iid) {

        List<Map<String, Object>> list = new ArrayList<>();
        ItemUser itemUser = new ItemUser();
        itemUser.setItem_id(Iid);
        List<ItemUser> itemUsers = itemUserMapper.select(itemUser);
        List<ProjectUser> projectUser;
        for (ItemUser u : itemUsers) {
            Map<String, Object> ItemUserInfo = new HashMap<>();
            projectUser = consumeProjectUser.findByUserId(u.getUser_id());
            ItemUserInfo.put("itemUser",u);
            ItemUserInfo.put("projectUser",projectUser);
            list.add(ItemUserInfo); //添加进list
        }
        return list;
    }

    @Override
    public void updateItemUser(ItemUser itemUser) {
        int i = itemUserMapper.updateByPrimaryKeySelective(itemUser);
        if(i != 1){
            throw new ItemException(ExceptionEnum.ITEM_USER_UPDATE_ERROR);
        }
        List<Integer> targetIds = itemUser.getTargetIds();
        itemUserMapper.DeleteTargetUserByUserId(itemUser.getItem_id(),itemUser.getUser_id());
        if (!CollectionUtils.isEmpty(targetIds)){
            for (Integer targetId : targetIds) {
                itemUserMapper.insertTargetUser(itemUser.getItem_id(),targetId,itemUser.getUser_id());
            }
        }
    }

    @Override
    @Transactional
    public void deleteItemUser(Integer id) {
        //先查询整条数据
        ItemUser itemUser = itemUserMapper.selectByPrimaryKey(id);
        int i = itemUserMapper.deleteByPrimaryKey(id);
        if(i != 1){
            throw new ItemException(ExceptionEnum.ITEM_USER_DELETE_ERROR);
        }
        //删除用户与指标的关系
        itemUserMapper.DeleteTargetUserByUserId(itemUser.getItem_id(),itemUser.getUser_id());
    }
}
