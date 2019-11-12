package com.xinlang.zly.project_user.controller;

import com.xinlang.bean.project_user.ProjectUserSkill;
import com.xinlang.zly.project_user.service.IProjectUserSkillService;
import com.xinlang.zly_xyx.log.LogAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import java.util.List;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019-11-09
 */
@RestController
public class ProjectUserSkillController {
    
    @Autowired
    private IProjectUserSkillService projectUserSkillService;

    /**
     * 全参不包括id
     * @param projectUserSkill
     * @return
     */
    @LogAnnotation(module = "添加技术标签")
    @PreAuthorize("hasAnyAuthority('project:skill:save')")
    @PostMapping("/skill")
    public ProjectUserSkill save(@RequestBody ProjectUserSkill projectUserSkill) {
        projectUserSkillService.save(projectUserSkill);
        return projectUserSkill;
    }

    /**
     * 全参不包括id
     * @param projectUserSkill
     */
    @LogAnnotation(module = "修改技术标签")
    @PreAuthorize("hasAnyAuthority('project:skill:update')")
    @PutMapping("/skill")
    public void update(@RequestBody ProjectUserSkill projectUserSkill) {
        projectUserSkillService.update(projectUserSkill);
    }

    /**
     * 根据系统用户表ID
     * @param userId
     * @return
     */
    @LogAnnotation(module = "根据系统用户表ID查询")
    @PreAuthorize("hasAnyAuthority('project:skill:qurey')")
    @GetMapping("/skill/{userId}")
    public List<ProjectUserSkill> findByUserId(@PathVariable Integer userId) {
        return projectUserSkillService.findByUserId(userId);
    }

    /**
     *  根据标签编码和用户类型查询
     * @param labelSign
     * @param userType
     * @return
     */
    @LogAnnotation(module = "根据用户类型和技术标签编码查询")
    @PreAuthorize("hasAnyAuthority('project:skill:qurey')")
    @GetMapping(value = "/skill/label-sign/user_type",params = {"labelSign", "userType"})
    public List<ProjectUserSkill> findByLabelSign(String labelSign,String userType) {
        return projectUserSkillService.findByLabelSign(labelSign,userType);
    }

    /**
     * 根据id删除
     * @param id
     */
    @LogAnnotation(module = "根据ID删除")
    @PreAuthorize("hasAnyAuthority('project:skill:qurey')")
    @DeleteMapping("/skill/{id}")
    public void delete(@PathVariable Integer id) {
        projectUserSkillService.delete(id);
    }
}