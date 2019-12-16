package com.xinlang.zly.summary.fegin;

import com.xinlang.bean.project_user.ProjectUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019-12-04
 */
@FeignClient("project-user")
public interface ConsumeProjectUser {

    @GetMapping("/user-anon/{userId}")
    List<ProjectUser> findByUserId(@PathVariable Integer userId);


}
