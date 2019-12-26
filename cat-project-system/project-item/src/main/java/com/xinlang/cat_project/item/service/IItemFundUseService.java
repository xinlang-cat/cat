package com.xinlang.cat_project.item.service;

import com.xinlang.cat_project.item.pojo.ItemFundUse;
import com.xinlang.zly_xyx.cat_common.service.IBaseService;

import java.util.List;
import java.util.Map;

public interface IItemFundUseService extends IBaseService<ItemFundUse> {

    void saveBill(Integer id, String bill);

    List<String> findBill(Integer id);

    void updateBill(Integer id, String bill);

    void deleteBill(Integer id);
}
