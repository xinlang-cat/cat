package com.xinlang.zly_xyx.company.feign;

import com.xinlang.zly_xyx.user.AppUser;
import com.xinlang.zly_xyx.user.SysRole;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("cat-user")
public interface ConsumeCatUser {
    @PostMapping("/users-anon/register")
    AppUser register(@RequestBody AppUser appUser);
    @PostMapping("/users/roles/default")
    void setDefaultRoleToUser(@RequestParam Long id, @RequestParam Long roleId);
    @GetMapping("/roles/code")
    SysRole findByCode(@RequestParam String code);

}
