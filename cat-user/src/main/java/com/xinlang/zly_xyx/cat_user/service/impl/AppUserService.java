package com.xinlang.zly_xyx.cat_user.service.impl;

import com.xinlang.zly_xyx.cat_common.utils.PhoneUtil;
import com.xinlang.zly_xyx.cat_user.mapper.AppUserMapper;
import com.xinlang.zly_xyx.cat_user.mapper.SysPermissionMapper;
import com.xinlang.zly_xyx.cat_user.mapper.UserCredentialsMapper;
import com.xinlang.zly_xyx.cat_user.mapper.UserRoleMapper;
import com.xinlang.zly_xyx.cat_user.service.IAppUserService;
import com.xinlang.zly_xyx.common.Page;
import com.xinlang.zly_xyx.user.AppUser;
import com.xinlang.zly_xyx.user.LoginAppUser;
import com.xinlang.zly_xyx.user.SysRole;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Set;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019/7/22
 */
@Service
@Slf4j
@Transactional
public class AppUserService implements IAppUserService {

    @Autowired
    private AppUserMapper appUserMapper;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private SysPermissionMapper sysPermissionMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private UserCredentialsMapper userCredentialsMapper;


    @Override
    public void addAppUser(AppUser appUser) {
        String username = appUser.getUsername();
        if(StringUtils.isBlank(username)){
            throw new IllegalArgumentException("用户名不能为空");
        }
        if(PhoneUtil.checkPhone(username)){

        }
    }

    @Override
    public void updateAppUser(AppUser appUser) {

    }

    @Override
    public LoginAppUser findByUsername(String username) {
        return null;
    }

    @Override
    public AppUser findById(Long id) {
        return null;
    }

    @Override
    public void setRoleToUser(Long id, Set<Long> roleIds) {

    }

    @Override
    public void updatePassword(Long id, String oldPassword, String newPassword) {

    }

    @Override
    public Page<AppUser> findUsers(Map<String, Object> params) {
        return null;
    }

    @Override
    public Set<SysRole> findRoleByUserId(Long userId) {
        return null;
    }

    @Override
    public void bindingPhone(Long userId, String phone) {

    }
}
