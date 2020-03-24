package com.xinlang.cat_project.item.mapper;

import com.xinlang.cat_project.item.pojo.ItemScheduling;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

public interface ItemSchedulingMapper extends Mapper<ItemScheduling>, InsertListMapper<ItemScheduling> {
}
