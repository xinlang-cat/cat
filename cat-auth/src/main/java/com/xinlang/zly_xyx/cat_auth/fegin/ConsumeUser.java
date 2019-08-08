package com.xinlang.zly_xyx.cat_auth.fegin;

import com.xinlang.zly_xyx.user.LoginAppUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019/7/22
 */
@FeignClient("cat-user")
public interface ConsumeUser {

    @GetMapping(value = "/users-anon/internal",params = "username")
    LoginAppUser findByUsername(@RequestParam("username")String username);

    @GetMapping("/wechat/login-check")
    void wechatLoginCheck(@RequestParam("tempCode")String tempCode,@RequestParam("openid")String openid);
}
