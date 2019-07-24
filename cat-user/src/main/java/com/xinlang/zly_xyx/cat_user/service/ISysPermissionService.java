package com.xinlang.zly_xyx.cat_user.service;

import com.xinlang.zly_xyx.common.Page;
import com.xinlang.zly_xyx.user.SysPermission;

import java.util.Map;
import java.util.Set;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019/7/22
 */
public interface ISysPermissionService {

    Set<SysPermission>  findPermission(Set<Long> roleIds);

    void save (SysPermission sysPermission);

    void update(SysPermission sysPermission);

    void del(Long id);

    Page<SysPermission> findPermissions(Map<String,Object> params);
}
