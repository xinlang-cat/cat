package com.xinlang.zly_xyx.company.controller;

import com.xinlang.zly_xyx.company.bean.CompanyUser;
import com.xinlang.zly_xyx.company.service.impl.CompanyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019-11-03
 */
@RestController
@RequestMapping("/companyUser")
public class CompanyUserController {
    @Autowired
    private CompanyUserService companyUserService;

    @PostMapping
    public CompanyUser save(@RequestBody CompanyUser companyUser) {
       companyUserService.save(companyUser);
       return companyUser;
    }

    @PutMapping
    public CompanyUser update(@RequestBody CompanyUser companyUser) {
       companyUserService.update(companyUser);
       return companyUser;
    }

    @GetMapping("/user/{userId}")
    public CompanyUser findByUserId(@PathVariable Integer userId) {
       return companyUserService.findByUserId(userId);
    }

    @GetMapping("/dept/{deptCode}")
    public List<CompanyUser> findByDeptCode(String deptCode) {
        return companyUserService.findByDeptCode(deptCode);
    }

    @DeleteMapping("/user/{userId}")
    public void deleteByUserId(@PathVariable Integer userId) {
       companyUserService.deleteByUserId(userId);
    }

    @DeleteMapping("/dept/{deptCode}")
    public void deleteByDeptCode(@PathVariable String deptCode) {
       companyUserService.deleteByDeptCode(deptCode);
    }
}