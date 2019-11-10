package com.xinlang.cat_project.item.fegin;

import com.xinlang.bean.project_user.ProjectUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("project-user")
public interface ConsumeProjectUser {

    /**
     * @param userId 根据系统用户表id查询
     * @return 单个实体
     */
    @GetMapping("/projectUser/internal/{userId}")
    ProjectUser findByUserId(@PathVariable Integer userId);

    /**
     * 获取项目管理所有用户
     * @return
     */
    @GetMapping("/all")
    List<ProjectUser> findAll();

}
