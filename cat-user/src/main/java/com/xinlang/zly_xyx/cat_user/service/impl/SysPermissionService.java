package com.xinlang.zly_xyx.cat_user.service.impl;

import com.xinlang.zly_xyx.cat_common.utils.PageUtil;
import com.xinlang.zly_xyx.cat_user.mapper.RolePermissionMapper;
import com.xinlang.zly_xyx.cat_user.mapper.SysPermissionMapper;
import com.xinlang.zly_xyx.cat_user.service.ISysPermissionService;
import com.xinlang.zly_xyx.common.Page;
import com.xinlang.zly_xyx.user.SysPermission;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019/7/22
 */
@Slf4j
@Service
public class SysPermissionService implements ISysPermissionService {

    @Autowired
    private SysPermissionMapper sysPermissionMapper;
    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    /**
     * 查询权限
     * @param roleIds
     * @return
     */
    @Override
    public Set<SysPermission> findPermission(Set<Long> roleIds) {
        return rolePermissionMapper.findPermissionsByRoleIds(roleIds);
    }

    /**
     * 添加
     * @param sysPermission
     */
    @Transactional
    @Override
    public void save(SysPermission sysPermission) {
        SysPermission permission = sysPermissionMapper.findByPermission(sysPermission.getPermission());
        if(permission != null){
            throw new IllegalArgumentException("该标识一存在");
        }
        sysPermission.setCreateTime(new Date());
        sysPermission.setUpdateTime(sysPermission.getCreateTime());
        sysPermissionMapper.save(sysPermission);
        log.info("保存权限标识:{}",sysPermission);
    }

    /**
     * 修改
     * @param sysPermission
     */
    @Transactional
    @Override
    public void update(SysPermission sysPermission) {
        sysPermission.setUpdateTime(new Date());
        sysPermissionMapper.update(sysPermission);
        log.info("修改权限标识:{}",sysPermission);
    }


    /**
     * 删除
     * @param id
     */
    @Transactional
    @Override
    public void del(Long id) {
        SysPermission sysPermission = sysPermissionMapper.findById(id);
        if(sysPermission == null){
            throw new IllegalArgumentException("标识符不存在");
        }
        sysPermissionMapper.delete(id);
        rolePermissionMapper.deleteRolePermission(null,id);
        log.info("删除标识符:{}",sysPermission);
    }

    /**
     * 分页查询
     * @param params
     * @return
     */
    @Override
    public Page<SysPermission> findPermissions(Map<String, Object> params) {
        int total = sysPermissionMapper.count(params);
        List<SysPermission>  permissions = Collections.emptyList();
        if(total>0){
            PageUtil.pageParamConver(params,false);
            permissions = sysPermissionMapper.findData(params);
        }
        return new Page<>(total,permissions                          );
    }
}
