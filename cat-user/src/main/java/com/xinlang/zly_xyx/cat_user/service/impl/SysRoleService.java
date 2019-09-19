package com.xinlang.zly_xyx.cat_user.service.impl;

import com.google.common.collect.Sets;
import com.xinlang.zly_xyx.cat_common.utils.PageUtil;
import com.xinlang.zly_xyx.cat_user.mapper.RolePermissionMapper;
import com.xinlang.zly_xyx.cat_user.mapper.SysRoleMapper;
import com.xinlang.zly_xyx.cat_user.mapper.UserRoleMapper;
import com.xinlang.zly_xyx.cat_user.service.ISysRoleService;
import com.xinlang.zly_xyx.common.Page;
import com.xinlang.zly_xyx.user.SysPermission;
import com.xinlang.zly_xyx.user.SysRole;
import com.xinlang.zly_xyx.user.constants.UserCenterMq;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019/7/22
 */
@Slf4j
@Service
public class SysRoleService implements ISysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private RolePermissionMapper rolePermissionMapper;
    @Autowired
    private AmqpTemplate amqpTemplate;

    /**
     * 添加
     * @param sysRole
     */
    @Transactional
    @Override
    public void save(SysRole sysRole) {
        SysRole sr = sysRoleMapper.findByCode(sysRole.getCode());
        if(sr != null){
            throw new IllegalArgumentException("角色已经存在");
        }
        sysRole.setCreateTime(new Date());
        sysRole.setUpdateTime(sysRole.getCreateTime());
        sysRoleMapper.save(sysRole);
        log.info("添加角色:{}",sysRole);
    }

    /**
     * 修改
     * @param sysRole
     */
    @Transactional
    @Override
    public void update(SysRole sysRole) {
        sysRole.setUpdateTime(new Date());
        sysRoleMapper.update(sysRole);
        log.info("修改角色:{}",sysRole);
    }

    /**
     * 删除角色
     * @param id
     */
    @Transactional
    @Override
    public void deleteRole(Long id) {
        SysRole sysRole = sysRoleMapper.findById(id);
        if(sysRole == null){
            throw new IllegalArgumentException("角色不存在");
        }
        sysRoleMapper.delete(id);
        rolePermissionMapper.deleteRolePermission(null,id);
        userRoleMapper.deleteUserRole(null,id);
        amqpTemplate.convertAndSend(UserCenterMq.MQ_EXCHANGE_USER,UserCenterMq.ROUTING_KEY_ROLE_DELETE,id);
        log.info("删除角色:{}",sysRole.getCode()  );
    }

    /**
     * 权限设置
     * @param id
     * @param permissionIds
     */
    @Transactional
    @Override
    public void setPermissionToRole(Long id, Set<Long> permissionIds) {
        SysRole sysRole  = sysRoleMapper.findById(id);
        if(sysRole == null){
            throw new IllegalArgumentException("角色不存在");
        }
        Set<Long> oldPermissionIds = rolePermissionMapper.findPermissionsByRoleIds(Sets.newHashSet(id)).stream()
                .map(p->p.getId()).collect(Collectors.toSet());
        Collection<Long> addPermissionIds = org.apache.commons.collections4.CollectionUtils.subtract(permissionIds,oldPermissionIds);
        if(!CollectionUtils.isEmpty(addPermissionIds)){
            addPermissionIds.forEach(permissionId ->rolePermissionMapper.saveRolePermission(id,permissionId));
        }
        Collection<Long> delPermissionIds = org.apache.commons.collections4.CollectionUtils.subtract(oldPermissionIds,permissionIds);
        if(!CollectionUtils.isEmpty(delPermissionIds)){
            delPermissionIds.forEach(delPermissionId->{
                rolePermissionMapper.deleteRolePermission(id,delPermissionId);
            });
        }
        log.info("给角色id:{},分配权限{}",id,permissionIds);
     }

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @Override
    public SysRole findById(Long id) {
        return sysRoleMapper.findById(id);
    }

    /**
     * 分页查询
     * @param params
     * @return
     */
    @Override
    public Page<SysRole> findRoles(Map<String, Object> params) {
        int total = sysRoleMapper.count(params);
        @SuppressWarnings("")
        List<SysRole> sysRoles = Collections.emptyList();
        if(total>0){
            PageUtil.pageParamConver(params,false);
            sysRoles = sysRoleMapper.findData(params);
        }
        return new Page<>(total,sysRoles);
    }

    /**
     * 根据角色获取权限列表
     * @param roleId
     * @return
     */
    @Override
    public Set<SysPermission> findPermissionsByRoleId(Long roleId) {
        return rolePermissionMapper.findPermissionsByRoleIds(Sets.newHashSet(roleId));
    }
}
