package com.xinlang.zly.project_user.controller;

import com.xinlang.bean.project_user.ProjectUserSkill;
import com.xinlang.zly.project_user.service.IProjectUserSkillService;
import com.xinlang.zly_xyx.cat_common.utils.AppUserUtil;
import com.xinlang.zly_xyx.log.LogAnnotation;
import com.xinlang.zly_xyx.user.AppUser;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019-11-09
 */
@RestController
public class ProjectUserSkillController {
    
    @Autowired
    private IProjectUserSkillService projectUserSkillService;

    @ApiOperation(value = "添加用户技术标签， 全参不包括id")
    @LogAnnotation(module = "添加用户技术标签")
    @PostMapping("/skill")
    public ProjectUserSkill save(@RequestBody ProjectUserSkill projectUserSkill) {
        projectUserSkillService.save(projectUserSkill);
        return projectUserSkill;
    }
    @PostMapping("/toSkills")
    @ApiOperation(value = "全参不包括id")
    @LogAnnotation(module = "添加用户所能服务的具体产业或领域")
    public void save(String userType ,@RequestBody Set<String> signs) {
        AppUser appUser = AppUserUtil.getLoginAppUser();
        Integer userId = appUser.getId().intValue();
        ProjectUserSkill skill = new ProjectUserSkill();
        skill.setCreateTime(new Date());
        skill.setUserId(userId);
        skill.setUserType(userType);
        signs.forEach(sign->{
            skill.setLabelSign(sign);
            projectUserSkillService.save(skill);
        });
    }

    @LogAnnotation(module = "修改用户技术标签")
    @ApiOperation(value = "修改用户技术标签，id必填")
    @PutMapping("/skill")
    public void update(@RequestBody ProjectUserSkill projectUserSkill) {
        projectUserSkillService.update(projectUserSkill);
    }

    @ApiOperation(value = "根据系统用户表ID查询")
    @LogAnnotation(module = "根据系统用户表ID查询用户技术标签")
    @GetMapping("/skill-anon/{userId}")
    public List<ProjectUserSkill> findByUserId(@PathVariable Integer userId) {
        return projectUserSkillService.findByUserId(userId);
    }

    @ApiOperation(value = "根据用户类型和技术标签编码查询")
    @LogAnnotation(module = "根据用户类型和技术标签编码查询")
    @GetMapping(value = "/skill/label-sign/user_type",params = {"labelSign", "userType"})
    public List<ProjectUserSkill> findByLabelSign(String labelSign,String userType) {
        return projectUserSkillService.findByLabelSign(labelSign,userType);
    }

    @ApiOperation(value = "根据ID删除")
    @LogAnnotation(module = "根据ID删除用户技术标签")
    @DeleteMapping("/skill/{id}")
    public void delete(@PathVariable Integer id) {
        projectUserSkillService.delete(id);
    }
}