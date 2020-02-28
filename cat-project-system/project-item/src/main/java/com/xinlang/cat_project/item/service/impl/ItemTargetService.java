package com.xinlang.cat_project.item.service.impl;

import com.xinlang.cat_project.item.enums.ExceptionEnum;
import com.xinlang.cat_project.item.exception.ItemException;
import com.xinlang.cat_project.item.fegin.ConsumeProjectUser;
import com.xinlang.cat_project.item.mapper.ItemContentMapper;
import com.xinlang.cat_project.item.mapper.ItemTargetMapper;
import com.xinlang.cat_project.item.mapper.ItemUserMapper;
import com.xinlang.cat_project.item.pojo.ItemTarget;
import com.xinlang.cat_project.item.pojo.modifyApply;
import com.xinlang.cat_project.item.service.IItemTargetService;
import com.xinlang.zly_xyx.cat_common.service.impl.BaseService;
import com.xinlang.zly_xyx.cat_common.utils.AppUserUtil;
import com.xinlang.zly_xyx.cat_common.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@Transactional
public class ItemTargetService extends BaseService<ItemTarget> implements IItemTargetService {

    @Autowired
    private ItemTargetMapper itemTargetMapper;

    @Autowired
    private ItemContentMapper itemContentMapper;

    @Autowired
    private ItemUserMapper itemUserMapper;

    @Autowired
    private ConsumeProjectUser consumeProjectUser;


    @Override
    public void saveTargets(List<ItemTarget> itemTargets) {
        int count = itemTargetMapper.insertList(itemTargets);
        for (ItemTarget itemTarget : itemTargets) {
            try {
                String userIds = itemTarget.getUserIds();
                for (String userId : userIds.split(",")) {
                    itemUserMapper.insertTargetUser(itemTarget.getItem_id(),itemTarget.getId(),Integer.parseInt(userId));
                }
            } catch (Exception e) {
                continue;
            }
        }
        if (count < 1){
            throw new ItemException(ExceptionEnum.SAVE_ERROR);
        }
    }

    @Override
    public void deleteTargetByItemId(Integer item_id) {
        ItemTarget itemTarget = new ItemTarget();
        itemTarget.setItem_id(item_id);
        itemTargetMapper.delete(itemTarget);
    }

    @Override
    public List<Integer> findTargetUsers(Integer target_id, Integer item_id) {
        return itemUserMapper.selectTargetUserByTarget(target_id,item_id);
    }

    @Override
    public List<ItemTarget> findQuantity(Integer weth) {
        Example example = new Example(ItemTarget.class);
        if (weth==1){
            //数量指标
            example.createCriteria().andIsNotNull("count");
        }else if (weth==2){
            //非数量指标
            example.createCriteria().andIsNull("count");
        }
        List<ItemTarget> list = itemTargetMapper.selectByExample(example);
        return list;
    }
}
