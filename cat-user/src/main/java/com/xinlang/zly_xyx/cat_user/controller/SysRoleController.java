package com.xinlang.zly_xyx.cat_user.controller;

import com.xinlang.zly_xyx.cat_user.service.ISysRoleService;
import com.xinlang.zly_xyx.common.Page;
import com.xinlang.zly_xyx.user.SysPermission;
import com.xinlang.zly_xyx.user.SysRole;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Set;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019/7/23
 */
@RestController
public class SysRoleController {

    @Autowired
    private ISysRoleService sysRoleService;


    /**
     * 添加角色
     * @param sysRole
     * @return
     */
    @PreAuthorize("hasAnyAuthority('back:role:save')")
    @PostMapping("/roles")
    public SysRole save(@RequestBody SysRole  sysRole){
        if(StringUtils.isBlank(sysRole.getCode())){
            throw new IllegalArgumentException("角色code不能为空") ;
        }
        if(StringUtils.isBlank(sysRole.getName())){
            throw new IllegalArgumentException("角色名不能为空");
        }
        sysRoleService.save(sysRole);
        return sysRole;
    }

    /**
     * 修改角色
     * @param sysRole
     * @return
     */
    @PreAuthorize("hasAnyAuthority('back:role:update')")
    @PutMapping("/roles")
    public SysRole update(@RequestBody SysRole  sysRole){

        if(StringUtils.isBlank(sysRole.getName())){
            throw new IllegalArgumentException("角色名不能为空");
        }
        sysRoleService.update(sysRole);
        return sysRole;
    }


    /**
     *  删除角色
     * @param id
     * @return
     */
    @PreAuthorize("hasAnyAuthority('back:role:delete')")
    @DeleteMapping("/roles/{id}")
    public void delete(@PathVariable Long id){
        sysRoleService.deleteRole(id);
    }

    /**
     * 为角色添加权限
     * @param id
     * @param permissionIds
     */
    @PreAuthorize("hasAnyAuthority('back:role:permission:set')")
    @PostMapping("/roles/{id}/permissions")
    public void setPermissionToRole(@PathVariable Long id, @RequestBody Set<Long> permissionIds){
        sysRoleService.setPermissionToRole(id,permissionIds);
    }

    /**
     * 根据角色id获取权限列表
     * @param id
     * @return
     */
    @PreAuthorize("hasAnyAuthority('back:role:permission:set','role:permission:byroleid')")
    @GetMapping("/roles/{id}/permissions")
    public Set<SysPermission> findPermissionByRoleId(@PathVariable Long id){
        return sysRoleService.findPermissionsByRoleId(id);
    }

    /**
     * 根据id获取角色信息
     * @param id
     * @return
     */
    @PreAuthorize("hasAnyAuthority('back:role:query')")
    @GetMapping("/roles/{id}")
    public SysRole findById(@PathVariable Long id){
        return sysRoleService.findById(id);
    }

    @PreAuthorize("hasAnyAuthority('back:role:query')")
    @GetMapping("/riles")
    public Page<SysRole> findRoles(@RequestParam Map<String,Object> params){
        return sysRoleService.findRoles(params);
    }




}
