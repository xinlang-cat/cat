package com.xinlang.cat_project.item.mapper;

import com.xinlang.cat_project.item.pojo.ItemPersonnel;
import com.xinlang.cat_project.item.pojo.ItemPersonnelVice;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

public interface ItemPersonnelViceMapper extends Mapper<ItemPersonnelVice>, InsertListMapper<ItemPersonnelVice> {
}
