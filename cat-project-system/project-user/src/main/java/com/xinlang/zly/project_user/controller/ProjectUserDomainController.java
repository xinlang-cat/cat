package com.xinlang.zly.project_user.controller;

import com.xinlang.bean.project_user.ProjectUserDomain;
import com.xinlang.zly.project_user.service.IProjectUserDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019-11-09
 */
@RestController
public class ProjectUserDomainController {
    
    @Autowired
    private IProjectUserDomainService projectUserDomainService;

    /**
     * 全参不包括id
     * @param projectUserDomain
     * @return
     */
    @PostMapping("/domain")
    public ProjectUserDomain save(@RequestBody ProjectUserDomain projectUserDomain) {
        projectUserDomainService.save(projectUserDomain);
        return projectUserDomain;
    }

    /**
     * 全参不包括id
     * @param projectUserDomain
     */
    @PutMapping("/domain")
    public void update(@RequestBody ProjectUserDomain projectUserDomain) {
        projectUserDomainService.update(projectUserDomain);
    }

    /**
     * 根据系统用户表ID
     * @param userId
     * @return
     */
    @GetMapping("/domain/{userId}")
    public List<ProjectUserDomain> findByUserId(@PathVariable Integer userId) {
        return projectUserDomainService.findByUserId(userId);
    }

    /**
     *  根据标签编码和用户类型查询
     * @param labelSign
     * @param userType
     * @return
     */
    @GetMapping(value = "/domain/label_sign/user_type",params = {"labelSign", "userType"})
    public List<ProjectUserDomain> findByLabelSign(String labelSign,String userType) {
        return projectUserDomainService.findByLabelSign(labelSign,userType);
    }

    /**
     * 根据id删除
     * @param id
     */
    @DeleteMapping("/domain/{id}")
    public void delete(@PathVariable Integer id) {
        projectUserDomainService.delete(id);
    }
}