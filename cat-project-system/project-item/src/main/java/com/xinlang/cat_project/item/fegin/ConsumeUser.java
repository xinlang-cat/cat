package com.xinlang.cat_project.item.fegin;

import com.xinlang.zly.expert.bean.ProjectUser;
import com.xinlang.zly_xyx.cat_common.utils.AppUserUtil;
import com.xinlang.zly_xyx.user.LoginAppUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

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

    /**
     * 通过用户id查询用户详细信息
     * @param userId
     * @return
     */
    @GetMapping("/{id}")
    ProjectUser findByUserId(@PathVariable Integer userId);

    /**
     * 获取项目管理所有用户
     * @return
     */
    @GetMapping("/all")
    List<ProjectUser> findAll();
}
