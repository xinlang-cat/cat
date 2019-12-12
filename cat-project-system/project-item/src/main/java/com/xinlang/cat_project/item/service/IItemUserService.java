package com.xinlang.cat_project.item.service;

import com.xinlang.cat_project.item.pojo.ItemUser;
import com.xinlang.zly_xyx.cat_common.service.IBaseService;

import java.util.List;
import java.util.Map;

public interface IItemUserService extends IBaseService<ItemUser> {

    void saveitemUsers(List<ItemUser> itemUsers);
    /*添加人员与指标的关系*/
    void insertTargetUser(Integer item_id, Integer targetId, Integer user_id);
    /*查询人员与指标的关系*/
    List<Integer> selectTargetUserByUserId(Integer item_id, Integer user_id);

    void DeleteTargetUser(Integer id);
}
