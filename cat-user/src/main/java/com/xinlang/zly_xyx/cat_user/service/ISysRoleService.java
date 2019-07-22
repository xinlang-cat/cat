package com.xinlang.zly_xyx.cat_user.service;

import com.xinlang.zly_xyx.common.Page;
import com.xinlang.zly_xyx.user.SysPermission;
import com.xinlang.zly_xyx.user.SysRole;

import java.util.Map;
import java.util.Set;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019/7/22
 */
public interface ISysRoleService {
    void save(SysRole sysRole);

    void update(SysRole sysRole);

    void deleteRole(Long id);

    void setPermissionToRole(Long id, Set<Long> permissionIds);

    SysRole findById(Long id);

    Page<SysRole> findRoles(Map<String, Object> params);

    Set<SysPermission> findPermissionsByRoleId(Long roleId);
}
