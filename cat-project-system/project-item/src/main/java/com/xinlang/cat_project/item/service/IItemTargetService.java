package com.xinlang.cat_project.item.service;

import com.xinlang.cat_project.item.VO.TargetInfo;
import com.xinlang.cat_project.item.VO.TargetInfoAll;
import com.xinlang.cat_project.item.pojo.ItemTarget;

import java.util.List;

public interface IItemTargetService {

    void saveTarget(ItemTarget target);

    ItemTarget queryTargetById(Integer id);

    TargetInfo queryTargetInfoById(Integer id);

    List<ItemTarget> queryTargetByCId(Integer Cid);

    List<TargetInfoAll> queryTargetInfoAllByCId(Integer Cid);

    void updateTarget(ItemTarget target);

    void deleteTarget(Integer id);
}
