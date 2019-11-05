package com.xinlang.cat_project.item.service;

import com.xinlang.cat_project.item.pojo.ItemFund;

import java.util.List;

public interface IItemFundService {
    void saveFund(ItemFund itemFund);

    ItemFund queryFundById(Integer id);

    List<ItemFund> queryFundByIId(Integer Iid);

    void updateFund(ItemFund itemFund);

    void deleteFund(Integer id);
}
