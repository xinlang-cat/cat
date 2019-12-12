package com.xinlang.zly_xyx.company.controller;

import com.xinlang.zly_xyx.company.bean.CompanyUser;
import com.xinlang.zly_xyx.company.service.impl.CompanyUserService;
import com.xinlang.zly_xyx.log.LogAnnotation;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019-11-03
 */
@RestController
public class CompanyUserController {
    @Autowired
    private CompanyUserService companyUserService;

    @PostMapping("/user")
    @ApiOperation(value = "添加公司用户关系,全参")
    @LogAnnotation(module = "添加公司用户关系")
    public CompanyUser save(@RequestBody CompanyUser companyUser) {
       companyUserService.save(companyUser);
       return companyUser;
    }

    @PutMapping("/user")
    @ApiOperation(value = "修改公司用户关系,id必填")
    @LogAnnotation(module = "修改公司用户关系")
    public CompanyUser update(@RequestBody CompanyUser companyUser) {
       companyUserService.update(companyUser);
       return companyUser;
    }

    @GetMapping("/user/{userId}")
    @ApiOperation(value = "根据用户id查询")
    @LogAnnotation(module = "根据用户id查询")
    public CompanyUser findByUserId(@PathVariable Integer userId) {
       return companyUserService.findByUserId(userId);
    }

    @GetMapping("/user/dept/{deptCode}")
    @LogAnnotation(module = "根据公司代码查询list")
    @ApiOperation(value = "根据公司代码查询list")
    public List<CompanyUser> findByDeptCode(@PathVariable String deptCode) {
        return companyUserService.findByDeptCode(deptCode);
    }

    @DeleteMapping("/user/{userId}")
    @ApiOperation(value = "根据系统用户表id删除")
    @LogAnnotation(module = "根据系统用户表id删除")
    public void deleteByUserId(@PathVariable Integer userId) {
       companyUserService.deleteByUserId(userId);
    }

    @DeleteMapping("/user/dept/{deptCode}")
    @ApiOperation(value = "根据公司代码批量删除")
    @LogAnnotation(module = "根据公司代码批量删除")
    public void deleteByDeptCode(@PathVariable String deptCode) {
       companyUserService.deleteByDeptCode(deptCode);
    }
}