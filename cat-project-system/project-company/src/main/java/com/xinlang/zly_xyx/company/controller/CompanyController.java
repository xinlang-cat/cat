package com.xinlang.zly_xyx.company.controller;

import com.xinlang.bean.company.Company;
import com.xinlang.bean.project_user.ProjectUserType;
import com.xinlang.zly_xyx.cat_common.utils.AppUserUtil;
import com.xinlang.zly_xyx.common.Page;
import com.xinlang.bean.company.CompanyUser;
import com.xinlang.zly_xyx.company.feign.ConsumeCatUser;
import com.xinlang.zly_xyx.company.service.ICompanyService;
import com.xinlang.zly_xyx.company.service.ICompanyUserService;
import com.xinlang.zly_xyx.log.LogAnnotation;
import com.xinlang.zly_xyx.user.AppUser;
import com.xinlang.zly_xyx.user.constants.UserType;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019-09-25
 */
@RestController
public class CompanyController {

    @Autowired
    private ICompanyService companyService;
    @Autowired
    private ICompanyUserService companyUserService;
    @Autowired
    private ConsumeCatUser consumeCatUser;

    @PostMapping("/company-anon/save")
    @LogAnnotation(module = "添加公司、机构")
    @ApiOperation(value = "添加公司、机构")
    public Company save(@RequestBody Company company){
        //查重
        Company sqlCompany1 = companyService.findByDeptCode(company.getDeptCode());
        Company sqlCompany2 = companyService.findByName(company.getSignName());
        if(sqlCompany1 != null || sqlCompany2 != null){
            throw new IllegalArgumentException("公司已注册或代码不正确！");
        }
        company.setCreateTime(new Date());
        company.setEnabled(true);
        companyService.save(company);
        AppUser appUser = AppUserUtil.getLoginAppUser();
        if(appUser != null){
            consumeCatUser.setDefaultRoleToUser(appUser.getId(),consumeCatUser.findByCode(ProjectUserType.EXPERT.name()).getId());
        }
        if(appUser == null){
            appUser = new AppUser();
            appUser.setCreateTime(new Date());
            appUser.setUsername(company.getSignName());
            appUser.setNickname(company.getSignName());
            appUser.setPassword(company.getLegalPersonPhone());
            appUser.setType(UserType.BACKEND.name());
            appUser = consumeCatUser.register(appUser);
            Long roleId = consumeCatUser.findByCode(company.getIdentity() + ":DEPT").getId();
            consumeCatUser.setDefaultRoleToUser(appUser.getId(),roleId);
        }
        CompanyUser companyUser =  companyUserService.findByUserId(appUser.getId().intValue());
        if(companyUser == null){
            companyUser = new CompanyUser();
            companyUser.setDeptCode(company.getDeptCode());
            companyUser.setUserId(appUser.getId().intValue());
            companyUser.setName(appUser.getNickname());
            companyUserService.save(companyUser);
        }else {
            companyUser.setDeptCode(company.getDeptCode());
            companyUserService.update(companyUser);
        }
        return company;
    }

    @PutMapping("/company")
    @LogAnnotation(module = "修改公司、机构")
    @ApiOperation(value = "修改公司、机构")
    public Company update(@RequestBody Company company){
        if(company.getId() == null){
            throw new IllegalArgumentException("id is  not");
        }
        company.setDeptCode(null);
        company.setSignName(null);
        company.setUpdateTime(new Date());
        companyService.update(company);
        return company;
    }

    @GetMapping("/company/all")
    @LogAnnotation(module = "查询所有公司、机构")
    @ApiOperation(value = "查询所有公司、机构")
    public List<Company> findAll(){
        return companyService.findAll();
    }

    @GetMapping("/company/{deptCode}")
    @LogAnnotation(module = "根据机构编码查询公司、机构")
    @ApiOperation(value = "根据机构编码查询公司、机构")
    public Company findByDeptCode(@PathVariable String deptCode){
        return companyService.findByDeptCode(deptCode);
    }

    @GetMapping("/company/name/{name}")
    @LogAnnotation(module = "根据公司名称查询公司、机构")
    @ApiOperation(value = "根据公司名称查询公司、机构")
    public Company findByName(@PathVariable String name){
        return companyService.findByName(name);
    }

    @GetMapping("/company-anon/{userId}")
    @LogAnnotation(module = "根据系统用户表id查询公司、机构")
    @ApiOperation(value = "根据系统用户表id查询公司、机构")
    public Company findByUserId(@PathVariable Integer userId){
        if(userId == null){
            userId = AppUserUtil.getLoginAppUser().getId().intValue();
        }
        return companyService.findByUserId(userId);
    }

    @GetMapping("/company/now-user")
    @LogAnnotation(module = "获取当前用户的公司、机构")
    @ApiOperation(value = "获取当前用户的公司、机构")
    public Company findByNowUser(){
        return companyService.findByUserId(AppUserUtil.getLoginAppUser().getId().intValue());
    }

    @GetMapping("/companys")
    @LogAnnotation(module = "根据实体中的属性查询公司、机构")
    @ApiOperation(value = "根据实体中的属性查询")
    public Page<Company> findByParams(@RequestParam Map<String,Object> params){
        return companyService.findByParams(params);
    }

    @GetMapping("/company/link")
    @LogAnnotation(module = "根据实体中的属性查询公司、机构")
    @ApiOperation(value = "根据实体中的属性查询")
    public Page<Company> link(@RequestParam Map<String,Object> params){
        return companyService.link(params);
    }

    @DeleteMapping("/company/{deptCode}")
    @LogAnnotation(module = "删除公司及公司用户中间表信息")
    @ApiOperation(value = "删除公司及公司用户中间表信息")
    public void delete(@PathVariable String deptCode){
        companyService.delete(deptCode);
    }

}
