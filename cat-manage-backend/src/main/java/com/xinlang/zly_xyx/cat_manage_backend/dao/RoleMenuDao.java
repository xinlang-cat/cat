package com.xinlang.zly_xyx.cat_manage_backend.dao;

import java.util.List;
import java.util.Set;

import com.xinlang.zly_xyx.cat_manage_backend.model.Menu;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;


/**
 * 角色菜单关系<br>
 * 角色和菜单是多对多关系，role_menu是中间表
 *
*/
@Mapper
public interface RoleMenuDao {

	@Insert("insert into role_menu(roleId, menuId) values(#{roleId}, #{menuId})")
	int save(@Param("roleId") Long roleId, @Param("menuId") Long menuId);

	int delete(@Param("roleId") Long roleId, @Param("menuId") Long menuId);

	@Select("select t.menuId from role_menu t where t.roleId = #{roleId}")
	Set<Long> findMenuIdsByRoleId(Long roleId);

	List<Menu> findMenusByRoleIds(@Param("roleIds") Set<Long> roleIds);
}
