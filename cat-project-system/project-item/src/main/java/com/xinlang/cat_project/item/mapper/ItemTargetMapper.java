package com.xinlang.cat_project.item.mapper;

import com.xinlang.cat_project.item.VO.TargetInfo;
import com.xinlang.cat_project.item.VO.TargetInfoAll;
import com.xinlang.cat_project.item.pojo.ItemTarget;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ItemTargetMapper extends Mapper<ItemTarget> {

    TargetInfo selectTargetInfoById(Integer id);

    List<TargetInfoAll> selectTargetInfoAllById(Integer tid);

    @Insert("INSERT INTO item_user (item_id, target_id, user_id) VALUES (#{item_id},#{target_id},#{user_id})")
    int insertItemUser(@Param("item_id") Integer item_id, @Param("target_id") Integer target_id, @Param("user_id") Integer user_id);

    @Update("UPDATE item_user SET user_id = #{user_id} WHERE target_id = #{target_id} AND user_id = #{user_id}")
    int updateItemUser(@Param("target_id") Integer target_id, @Param("user_id") Integer user_id);
}
