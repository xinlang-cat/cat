package com.xinlang.zly_xyx.cat_user.controller;

import com.xinlang.zly_xyx.cat_user.service.ISysPermissionService;
import com.xinlang.zly_xyx.common.Page;
import com.xinlang.zly_xyx.user.SysPermission;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019/7/23
 */
@RestController
public class SysPermissionController {

    @Autowired
    private ISysPermissionService sysPermissionService;


    /**
     * 添加权限
     * @param sysPermission
     * @return
     */
    @PreAuthorize("hasAnyAuthority('back:permission:save')")
    @PostMapping("/permissions")
    public SysPermission save(@RequestBody SysPermission sysPermission){
        if(StringUtils.isBlank(sysPermission.getPermission())){
            throw new IllegalArgumentException("标识符不能为空");
        }
        if(StringUtils.isBlank(sysPermission.getName())){
            throw new IllegalArgumentException("权限名不能为空");
        }
        sysPermissionService.save(sysPermission);
        return sysPermission;
    }

    /**
     * 修改权限
     * @param sysPermission
     * @return
     */
    @PreAuthorize("hasAnyAuthority('back:permission:update')")
    @PutMapping("/permissions")
    public SysPermission update(@RequestBody SysPermission sysPermission){
        if(StringUtils.isBlank(sysPermission.getName())){
            throw new IllegalArgumentException("权限名不能为空");
        }
        sysPermissionService.update(sysPermission);
        return sysPermission;
    }

    /**
     * 删除
     * @param id
     */
    @PreAuthorize("hasAnyAuthority('back:permission:delete')")
    @GetMapping("/permissions/{id}")
    public void delete(@PathVariable Long id){
        sysPermissionService.del(id);
    }

    /**
     * 分页
     * @param params
     * @return
     */
    @PreAuthorize("hasAnyAuthority('back:permission:query')")
    @GetMapping
    public Page<SysPermission>  findPermissions(@RequestParam Map<String,Object> params){
        return sysPermissionService.findPersissions(params);
    }

}
