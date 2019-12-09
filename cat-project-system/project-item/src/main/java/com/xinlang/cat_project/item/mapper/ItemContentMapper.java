package com.xinlang.cat_project.item.mapper;

import com.xinlang.cat_project.item.pojo.ItemContent;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

public interface ItemContentMapper extends Mapper<ItemContent>, InsertListMapper<ItemContent> {
}
