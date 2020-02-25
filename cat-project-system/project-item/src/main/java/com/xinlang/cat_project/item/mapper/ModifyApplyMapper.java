package com.xinlang.cat_project.item.mapper;

import com.xinlang.cat_project.item.pojo.auditApply;
import com.xinlang.cat_project.item.pojo.modifyApply;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

public interface ModifyApplyMapper extends Mapper<modifyApply>, InsertListMapper<modifyApply> {



}
