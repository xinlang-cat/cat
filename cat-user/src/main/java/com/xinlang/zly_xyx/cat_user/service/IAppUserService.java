package com.xinlang.zly_xyx.cat_user.service;

import com.xinlang.zly_xyx.common.Page;
import com.xinlang.zly_xyx.user.AppUser;
import com.xinlang.zly_xyx.user.LoginAppUser;
import com.xinlang.zly_xyx.user.SysRole;

import java.util.Map;
import java.util.Set;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019/7/22
 */
public interface IAppUserService {

    void addAppUser(AppUser appUser);

    void updateAppUser(AppUser appUser);

    LoginAppUser findByUsername(String username);

    AppUser findById(Long id);

    void setRoleToUser(Long id, Set<Long> roleIds);

    void updatePassword(Long id,String oldPassword,String newPassword);

    Page<AppUser> findUsers(Map<String,Object> params);

    Set<SysRole>  findRoleByUserId(Long userId);

    void bindingPhone(Long userId,String phone);
}
