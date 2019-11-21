package com.xinlang.cat_project.item.mapper;

import com.xinlang.cat_project.item.VO.TargetInfo;
import com.xinlang.cat_project.item.VO.TargetInfoAll;
import com.xinlang.cat_project.item.pojo.ItemTarget;
import org.apache.ibatis.annotations.*;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ItemTargetMapper extends Mapper<ItemTarget> {

    TargetInfo selectTargetInfoById(Integer id);

    List<TargetInfoAll> selectTargetInfoAllById(Integer tid);

    /**
     * 添加成员与指标的关系
     * @param item_id
     * @param target_id
     * @param user_id
     * @return
     */
    @Insert("INSERT INTO item_user (item_id, target_id, user_id) VALUES (#{item_id},#{target_id},#{user_id})")
    int insertItemUser(@Param("item_id") Integer item_id, @Param("target_id") Integer target_id, @Param("user_id") Integer user_id);

    /**
     * 修改成员与指标的关系
     * @param target_id
     * @param user_id
     * @return
     */
    @Update("UPDATE item_user SET user_id = #{user_id} WHERE target_id = #{target_id} AND user_id = #{user_id}")
    int updateItemUser(@Param("target_id") Integer target_id, @Param("user_id") Integer user_id);

    /**
     * 删除人员的相关指标
     * @param target_id
     * @return
     */
    @Delete("DELETE FROM target_user WHERE target_id=#{target_id}")
    int DeleteTargetUserByUserId(@Param("target_id") Integer target_id);

    /**
     * 查询指标的相关人员
     * @param item_id
     * @param target_id
     * @return
     */
    @Select("SELECT user_id FROM target_user WHERE item_id=#{item_id} AND target_id=#{target_id}")
    List<Integer> selectTargetUserByTargetId(@Param("item_id") Integer item_id, @Param("target_id") Integer target_id);
}
