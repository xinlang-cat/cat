package com.xinlang.zly_xyx.company.controller;

import com.xinlang.zly_xyx.cat_common.utils.AppUserUtil;
import com.xinlang.zly_xyx.company.bean.Company;
import com.xinlang.zly_xyx.company.service.ICompanyService;
import com.xinlang.zly_xyx.log.LogAnnotation;
import com.xinlang.zly_xyx.user.LoginAppUser;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019-09-25
 */
@RestController
@RequestMapping
public class CompanyController {

    @Autowired
    private ICompanyService companyService;


    @PostMapping("/company")
    @LogAnnotation(module = "添加公司、机构")
    @ApiOperation(value = "添加公司、机构")
    public Company save(@RequestBody Company company){
        company.setCreateTime(new Date());
        company.setEnabled(true);
        companyService.save(company);
        return company;
    }

    @PutMapping("/company")
    @LogAnnotation(module = "修改公司、机构")
    @ApiOperation(value = "修改公司、机构")
    public Company update(@RequestBody Company company){
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

    @GetMapping("/company-anon/{userId}")
    @LogAnnotation(module = "根据系统用户表id查询公司、机构")
    @ApiOperation(value = "根据系统用户表id查询公司、机构")
    public Company findByUserId(@PathVariable Integer userId){
        return companyService.findByUserId(userId);
    }

    @DeleteMapping("/company/{deptCode}")
    @LogAnnotation(module = "删除公司及公司用户中间表信息")
    @ApiOperation(value = "删除公司及公司用户中间表信息")
    public void delete(@PathVariable String deptCode){
        companyService.delete(deptCode);
    }



}
