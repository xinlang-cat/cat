package com.xinlang.zly.project_user.controller;

import com.xinlang.bean.project_user.ProjectUserDomain;
import com.xinlang.zly.project_user.service.IProjectUserDomainService;
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
public class ProjectUserDomainController {
    
    @Autowired
    private IProjectUserDomainService projectUserDomainService;

    @PostMapping("/domain")
    @ApiOperation(value = "全参不包括id")
    @LogAnnotation(module = "添加用户所能服务的具体产业或领域")
    public ProjectUserDomain save(@RequestBody ProjectUserDomain projectUserDomain) {
        projectUserDomainService.save(projectUserDomain);
        return projectUserDomain;
    }
    @PostMapping("/toDomains")
    @ApiOperation(value = "全参不包括id")
    @LogAnnotation(module = "添加用户所能服务的具体产业或领域")
    public void save(String userType,@RequestBody Set<String> signs) {
        AppUser appUser = AppUserUtil.getLoginAppUser();
        Integer userId = appUser.getId().intValue();
        ProjectUserDomain domain = new ProjectUserDomain();
        domain.setCreateTime(new Date());
        domain.setUserId(userId);
        domain.setUserType(userType);
        signs.forEach(sign->{
            domain.setLabelSign(sign);
            projectUserDomainService.save(domain);
        });
    }

    @PutMapping("/domain")
    @ApiOperation(value = "修改，id必填")
    @LogAnnotation(module = "修改用户所能服务的具体产业或领域")
    public void update(@RequestBody ProjectUserDomain projectUserDomain) {
        projectUserDomainService.update(projectUserDomain);
    }

    @GetMapping("/domain/{userId}")
    @ApiOperation(value = "根据系统用户表ID查询")
    @LogAnnotation(module = "根据系统用户表ID查询用户所能服务的具体产业或领域")
    public List<ProjectUserDomain> findByUserId(@PathVariable Integer userId) {
        return projectUserDomainService.findByUserId(userId);
    }

    @GetMapping(value = "/domain/label-sign/user_type",params = {"labelSign", "userType"})
    @ApiOperation(value = "根据标签编码和用户类型查询")
    @LogAnnotation(module = "根据标签编码和用户类型查询查询用户所能服务的具体产业或领域")
    public List<ProjectUserDomain> findByLabelSign(String labelSign,String userType) {
        return projectUserDomainService.findByLabelSign(labelSign,userType);
    }

    @DeleteMapping("/domain/{id}")
    @ApiOperation(value = "根据id删除")
    @LogAnnotation(module = "根据id删除用户所能服务的具体产业或领域")
    public void delete(@PathVariable Integer id) {
        projectUserDomainService.delete(id);
    }
}