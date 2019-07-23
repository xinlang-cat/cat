package com.xinlang.zly_xyx.cat_user.controller;

import com.xinlang.zly_xyx.cat_user.service.IAppUserService;
import com.xinlang.zly_xyx.user.LoginAppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019/7/23
 */
@RestController
public class UserController {
    @Autowired
    private IAppUserService appUserService;


}
