package com.xinlang.cat_project.item.mapper;

import com.xinlang.bean.projectInfo.ItemPersonnel;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

public interface ItemPersonnelMapper extends Mapper<ItemPersonnel>, InsertListMapper<ItemPersonnel> {
}
