package com.xinlang.cat_project.item.fegin;

import com.xinlang.zly_xyx.user.LoginAppUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("cat-user")
public interface ConsumeUser {

    /**
     * 根据用户名查找用户信息
     * @param username
     * @return
     */
    @GetMapping(value = "/users-anon/internal",params = "username")
    LoginAppUser findByUsername(@RequestParam("username")String username);

    /**
     * 获取当前登录用户
     * @return
     */
    @GetMapping("/users/current")
    LoginAppUser getLoginAppUser();

}
