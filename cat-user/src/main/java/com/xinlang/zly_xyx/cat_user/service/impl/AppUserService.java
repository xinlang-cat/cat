package com.xinlang.zly_xyx.cat_user.service.impl;

import com.xinlang.zly_xyx.cat_common.utils.EmailUtil;
import com.xinlang.zly_xyx.cat_common.utils.PageUtil;
import com.xinlang.zly_xyx.cat_common.utils.PhoneUtil;
import com.xinlang.zly_xyx.cat_user.mapper.*;
import com.xinlang.zly_xyx.cat_user.service.IAppUserService;
import com.xinlang.zly_xyx.common.Page;
import com.xinlang.zly_xyx.user.*;
import com.xinlang.zly_xyx.user.constants.CredentialType;
import com.xinlang.zly_xyx.user.constants.UserType;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019/7/22
 */
@Service
@Slf4j
public class AppUserService implements IAppUserService {

    @Autowired
    private AppUserMapper appUserMapper;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private UserCredentialsMapper userCredentialsMapper;
    @Autowired
    private RolePermissionMapper rolePermissionMapper;


    /**
     * 添加用户
     * @param appUser
     */
    @Transactional
    @Override
    public void addAppUser(AppUser appUser) {
        String username = appUser.getUsername();
        if(StringUtils.isBlank(username)){
            throw new IllegalArgumentException("用户名不能为空");
        }
//        if(PhoneUtil.checkPhone(username)){
//            throw new IllegalArgumentException("用户名不能使用纯数字");
//        }
        if(EmailUtil.emailFormat(username) || username.contains("|")){
            throw new IllegalArgumentException("用户名包含非法字符");
        }
        if(StringUtils.isBlank(appUser.getPassword())){
            throw new IllegalArgumentException("密码不能为空");
        }
        if(StringUtils.isBlank(appUser.getNickname())){
            appUser.setNickname(username);
        }
        if(StringUtils.isBlank(appUser.getType())){
            appUser.setType(UserType.APP.name());
        }
        UserCredential userCredential = userCredentialsMapper.findByUsername(username);
        if(userCredential != null){
            throw new IllegalArgumentException("用户名已存在");
        }
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        appUser.setEnabled(Boolean.TRUE);
        appUser.setCreateTime(new Date());
        appUser.setUpdateTime(appUser.getCreateTime());
        appUserMapper.save(appUser);
        userCredentialsMapper.save(new UserCredential(appUser.getUsername(), CredentialType.USERNAME.name(),appUser.getId()));
        log.info("添加用户:{}",appUser);
    }

    /**
     * 修改用户
     * @param appUser
     */
    @Transactional
    @Override
    public void updateAppUser(AppUser appUser) {
        appUser.setUpdateTime(new Date());
        appUserMapper.update(appUser);
        log.info("修改用户:{}",appUser);
    }

    /**
     * 获取当前用户
     * @param username
     * @return
     */
    @Transactional
    @Override
    public LoginAppUser findByUsername(String username) {
        AppUser appUser = userCredentialsMapper.findUserByUsername(username);
        if(appUser == null) {
           return null;
        }
        LoginAppUser loginAppUser = new LoginAppUser();
        BeanUtils.copyProperties(appUser, loginAppUser);
        Set<SysRole> sysRoles = userRoleMapper.findRolesByUserId(appUser.getId());
        loginAppUser.setSysRoles(sysRoles);
        if (!CollectionUtils.isEmpty(sysRoles)) {
            Set<Long> roleIds = sysRoles.parallelStream().map(SysRole::getId).collect(Collectors.toSet());
            Set<SysPermission> sysPermissions = rolePermissionMapper.findPermissionsByRoleIds(roleIds);
            if (!CollectionUtils.isEmpty(sysPermissions)) {
                Set<String> permissions = sysPermissions.parallelStream().map(SysPermission::getPermission).collect(Collectors.toSet());
                loginAppUser.setPermissions(permissions);
            }
        }
        return loginAppUser;
    }


    @Transactional
    @Override
    public AppUser findById(Long id) {
        return appUserMapper.findById(id);
    }

    /**
     * j角色设置/修改
     * @param id
     * @param roleIds
     */
    @Transactional
    @Override
    public void setRoleToUser(Long id, Set<Long> roleIds) {
        AppUser appUser = appUserMapper.findById(id);
        if(appUser == null){
            throw new IllegalArgumentException("用户不存在");
        }
        userRoleMapper.deleteUserRole(id,null);
        if(!CollectionUtils.isEmpty(roleIds)){
            roleIds.forEach( roleId->{
                userRoleMapper.saveUserRoles(id,roleId);
            });
        }
        log.info("修改用户:{}的角色,{}",appUser.getUsername(),roleIds);
    }

    /**
     * 修改密码
     * @param id
     * @param oldPassword
     * @param newPassword
     */
    @Transactional
    @Override
    public void updatePassword(Long id, String oldPassword, String newPassword) {
        AppUser appUser = appUserMapper.findById(id);
        if(StringUtils.isNoneBlank(oldPassword)){
            if(!passwordEncoder.matches(oldPassword,appUser.getPassword())){
                throw new IllegalArgumentException("旧密码错误");
            }
        }
        AppUser u = new AppUser();
        u.setId(appUser.getId());
        u.setPassword(passwordEncoder.encode(newPassword));
        updateAppUser(u);
        log.info("修改密码:{}",u);
    }

    /**
     * 分页查询
     * @param params
     * @return
     */
    @Override
    public Page<AppUser> findUsers(Map<String, Object> params) {
        int total = appUserMapper.count(params);
        @SuppressWarnings("如果为空返回空的List")
        List<AppUser> appUsers = Collections.emptyList();
        if(total >0){
            PageUtil.pageParamConver(params,true);
            appUsers = appUserMapper.findData(params);
        }
        return new Page<>(total,appUsers);
    }

    @Override
    public List<AppUser> findAll() {
        return appUserMapper.findAll();
    }

    @Override
    public Set<SysRole> findRoleByUserId(Long userId) {
        return userRoleMapper.findRolesByUserId(userId);
    }

    /**
     * 绑定手机
     * @param userId
     * @param phone
     */
    @Transactional
    @Override
    public void bindingPhone(Long userId, String phone) {
        UserCredential userCredential = userCredentialsMapper.findByUsername(phone);
        if(userCredential == null){
            throw new IllegalArgumentException("号码已绑定其他账户");
        }
        AppUser appUser = appUserMapper.findById(userId);
        appUser.setPhone(phone);
        userCredentialsMapper.save(new UserCredential(phone,CredentialType.PHONE.name(),userId));
        log.info("绑定手机号码，用户:{},号码:{}",appUser.getUsername(),phone);
    }
}
