package com.xinlang.cat_project.item.service;

import com.xinlang.cat_project.item.pojo.ItemFundUse;

import java.util.List;
import java.util.Map;

public interface IItemFundUseService {

    void saveFundUse(ItemFundUse itemFundUse);

    ItemFundUse queryFundUseById(Integer id);

    List<ItemFundUse> queryFundUseByBId(Integer Bid);

    List<ItemFundUse> queryFundUseByItemIdAndUserId(Integer iid, int userTd);

    void updateFundUse(ItemFundUse itemFundUse);

    void deleteFundUse(Integer id);
}
