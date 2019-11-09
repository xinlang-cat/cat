package com.xinlang.zly.project_user.controller;

import com.xinlang.bean.project_user.ProjectUser;
import com.xinlang.bean.project_user.ProjectUserDomain;
import com.xinlang.bean.project_user.ProjectUserSkill;
import com.xinlang.zly.project_user.mapper.ProjectUserDomainMapper;
import com.xinlang.zly.project_user.mapper.ProjectUserSkillMapper;
import com.xinlang.zly.project_user.service.IProjectUserDomainService;
import com.xinlang.zly.project_user.service.IProjectUserSkillService;
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
    @Autowired
    private IProjectUserSkillService projectUserSkillService;
    @Autowired
    private IProjectUserDomainService projectUserDomainService;

    @PostMapping("/user")

    public ProjectUser save(@RequestBody ProjectUser projectUser){
        projectUserService.save(projectUser);
        return projectUser;
    }

    @PutMapping("/user")
    public ProjectUser update(@RequestBody ProjectUser projectUser){
        projectUserService.update(projectUser);
        return projectUser;
    }

    /**
     * @return 所有的实体
     */
    @GetMapping("/user/all")
    public List<ProjectUser> findAll(){
        List<ProjectUser> list =  projectUserService.findAll();
        setDomainAndSkill(list);
        return list;
    }

    /**
     * @param userType 根据类型查询，例如专家，监理，等类型
     * @return 所有该类型的实体
     */
    @GetMapping("/user/type/{userType}")
    public List<ProjectUser> findByUserType(@PathVariable String userType){
        List<ProjectUser> list =  projectUserService.findByUserType(userType);
        setDomainAndSkill(list);
        return list;
    }

    /**
     * @param userId 根据系统用户表id查询
     * @return 单个实体
     */
    @GetMapping("/user-anon/{userId}")
    public List<ProjectUser> findByUserId(@PathVariable Integer userId){
        List<ProjectUser> list =   projectUserService.findByUserId(userId);
        setDomainAndSkill(list);
        return list;
    }

    /**
     * @param userId 根据系统用户表id删除
     */
    @DeleteMapping("/user/{userId}")
    public void deleteByUserId(@PathVariable Integer userId){
        projectUserService.deleteByUserId(userId);
    }


    private void setDomainAndSkill(List<ProjectUser> list){
        list.forEach(pu->{
            List<ProjectUserDomain> domains = projectUserDomainService.findByUserId(pu.getUserId());
            List<ProjectUserSkill> skills = projectUserSkillService.findByUserId(pu.getUserId());
            pu.setDomains(domains);
            pu.setSkills(skills);
        });
    }

}
