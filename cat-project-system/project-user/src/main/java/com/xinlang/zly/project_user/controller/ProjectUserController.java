package com.xinlang.zly.project_user.controller;

import com.xinlang.bean.project_user.ProjectUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.xinlang.zly.project_user.service.IProjectUserService;

import java.util.List;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019-11-05
 */
@RestController
public class ProjectUserController {

    @Autowired
    private IProjectUserService projectUserService;

    @PostMapping("/projectUser")
    public ProjectUser save(@RequestBody ProjectUser projectUser){
        projectUserService.save(projectUser);
        return projectUser;
    }

    @PutMapping("/projectUser")
    public ProjectUser update(@RequestBody ProjectUser projectUser){
        projectUserService.update(projectUser);
        return projectUser;
    }

    /**
     * @return 所有的实体
     */
    @GetMapping("/projectUser/all")
    public List<ProjectUser> findAll(){
        return projectUserService.findAll();
    }

    /**
     * @param userType 根据类型查询，例如专家，监理，等类型
     * @return 所有该类型的实体
     */
    @GetMapping("/projectUser/type/{userType}")
    public List<ProjectUser> findByUserType(@PathVariable String userType){
        return projectUserService.findByUserType(userType);
    }

    /**
     * @param userId 根据系统用户表id查询
     * @return 单个实体
     */
    @GetMapping("/projectUser-anon/{userId}")
    public ProjectUser findByUserId(@PathVariable Integer userId){
        return projectUserService.findByUserId(userId);
    }

    /**
     * @param userId 根据系统用户表id删除
     */
    @DeleteMapping("/projectUser/{userId}")
    public void deleteByUserId(@PathVariable Integer userId){
        projectUserService.deleteByUserId(userId);
    }

}
