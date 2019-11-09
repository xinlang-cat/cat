package com.xinlang.cat_project.item.service;

import com.xinlang.cat_project.item.pojo.ItemTarget;

import java.util.List;
import java.util.Map;

public interface IItemTargetService {

    void saveTarget(ItemTarget target);

    ItemTarget queryTargetById(Integer id);

    List<Map<String, Object>> queryTargetByCId(Integer Cid);

    void updateTarget(ItemTarget target);

    void deleteTarget(Integer id);
}
