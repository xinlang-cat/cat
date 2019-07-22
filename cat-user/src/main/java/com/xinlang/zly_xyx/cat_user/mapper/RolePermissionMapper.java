package com.xinlang.zly_xyx.cat_user.mapper;

import com.xinlang.zly_xyx.user.SysPermission;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * 角色权限关系<br>
 * 角色和权限是多对多关系，sys_role_permission是中间表
 * @author 张龙毅 18777811286@163.com
 * 2019/7/22
 */
@Mapper
@Repository("rolePermissionMapper")
public interface RolePermissionMapper {

	@Insert("insert into sys_role_permission(roleId, permissionId) values(#{roleId}, #{permissionId})")
	int saveRolePermission(@Param("roleId") Long roleId, @Param("permissionId") Long permissionId);

	int deleteRolePermission(@Param("roleId") Long roleId, @Param("permissionId") Long permissionId);

	Set<SysPermission> findPermissionsByRoleIds(@Param("roleIds") Set<Long> roleIds);

}
