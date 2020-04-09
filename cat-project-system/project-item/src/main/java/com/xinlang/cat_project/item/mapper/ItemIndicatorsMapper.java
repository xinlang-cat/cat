package com.xinlang.cat_project.item.mapper;

import com.xinlang.bean.projectInfo.ItemIndicators;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

import java.util.List;

public interface ItemIndicatorsMapper extends Mapper<ItemIndicators>, InsertListMapper<ItemIndicators> {

    @Select({"<script>", "select", "*", "from item_indicators", "where id in", "<foreach collection='ids' item='id' open='(' separator=',' close=')'>",
            "#{id}", "</foreach>", "</script>"})
    List<ItemIndicators> getByIds(@Param("ids") List<Integer> ids);
}
