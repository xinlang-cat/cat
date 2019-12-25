package com.xinlang.cat_project.item.mapper;

import com.xinlang.cat_project.item.pojo.ItemUser;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

import java.util.List;

public interface ItemUserMapper extends Mapper<ItemUser>, InsertListMapper<ItemUser> {
    /**
     * 添加成员与指标的关系
     * @param target_id
     * @param user_id
     * @return
     */
    @Insert("INSERT INTO target_user (item_id,target_id, user_id) VALUES (#{item_id},#{target_id},#{user_id})")
    int insertTargetUser(@Param("item_id") Integer item_id, @Param("target_id") Integer target_id, @Param("user_id") Integer user_id);

    /**
     * 查询人员的相关指标
     * @param item_id
     * @param user_id
     * @return
     */
    @Select("SELECT target_id FROM target_user WHERE item_id=#{item_id} AND user_id=#{user_id}")
    List<Integer> selectTargetUserByUserId(@Param("item_id") Integer item_id, @Param("user_id") Integer user_id);

    /**
     * 删除人员的相关指标
     * @param item_id
     * @param user_id
     * @return
     */
    @Delete("DELETE FROM target_user WHERE item_id=#{item_id} AND user_id=#{user_id}")
    int DeleteTargetUserByUserId(@Param("item_id") Integer item_id, @Param("user_id") Integer user_id);
    /**
     * 删除全部人员的相关指标
     * @param item_id
     * @param item_id
     * @return
     */
    @Delete("DELETE FROM target_user WHERE item_id=#{item_id}")
    void DeleteTargetUserByItemId(Integer item_id);
}
