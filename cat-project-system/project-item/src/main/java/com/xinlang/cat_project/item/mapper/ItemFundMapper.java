package com.xinlang.cat_project.item.mapper;

import com.xinlang.cat_project.item.pojo.ItemFund;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

public interface ItemFundMapper extends Mapper<ItemFund>, InsertListMapper<ItemFund> {
}
