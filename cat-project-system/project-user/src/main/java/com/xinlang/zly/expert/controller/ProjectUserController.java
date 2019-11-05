package com.xinlang.zly.expert.controller;

import com.xinlang.zly.expert.bean.ProjectUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.xinlang.zly.expert.service.IProjectUserService;

import java.util.List;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019-11-05
 */
@RestController
@RequestMapping("/projectUser")
public class ProjectUserController {

    @Autowired
    private IProjectUserService projectUserService;

    @PostMapping
    public ProjectUser save(@RequestBody ProjectUser projectUser){
        projectUserService.save(projectUser);
        return projectUser;
    }

    @PutMapping
    public ProjectUser update(@RequestBody ProjectUser projectUser){
        projectUserService.update(projectUser);
        return projectUser;
    }

    @GetMapping("/all")
    public List<ProjectUser> findAll(){
        return projectUserService.findAll();
    }

    @GetMapping("/type/{userType}")
    public List<ProjectUser> findByUserType(@PathVariable String userType){
        return projectUserService.findByUserType(userType);
    }

    @GetMapping("/{id}")
    public ProjectUser findByUserId(@PathVariable Integer userId){
        return projectUserService.findByUserId(userId);
    }

    @DeleteMapping("/{id}")
    public void deleteByUserId(@PathVariable Integer userId){
        projectUserService.deleteByUserId(userId);
    }

}
