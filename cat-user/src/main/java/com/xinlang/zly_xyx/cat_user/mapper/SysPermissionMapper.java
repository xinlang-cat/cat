package com.xinlang.zly_xyx.cat_user.mapper;

import com.xinlang.zly_xyx.user.SysPermission;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019/7/22
 */
@Mapper
public interface SysPermissionMapper {

	@Options(useGeneratedKeys = true, keyProperty = "id")
	@Insert("insert into sys_permission(permission, name, createTime, updateTime) values(#{permission}, #{name}, #{createTime}, #{createTime})")
	int save(SysPermission sysPermission);

	@Update("update sys_permission t set t.name = #{name}, t.permission = #{permission}, t.updateTime = #{updateTime} where t.id = #{id}")
	int update(SysPermission sysPermission);

	@Delete("delete from sys_permission where id = #{id}")
	int delete(Long id);

	@Select("select * from sys_permission t where t.id = #{id}")
	SysPermission findById(Long id);

	@Select("select * from sys_permission t where t.permission = #{permission}")
	SysPermission findByPermission(String permission);

	int count(Map<String, Object> params);

	List<SysPermission> findData(Map<String, Object> params);

}
