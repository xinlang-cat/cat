package com.xinlang.zly.project_user.fegin;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("cat-user")
public interface ConsumeCatUser {
    @PostMapping("/users/roles/default")
    void setDefaultRoleToUser(@RequestParam Long id, @RequestParam Long roleId);
}
