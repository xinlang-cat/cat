package com.xinlang.zly_xyx.company.controller;

import com.xinlang.zly_xyx.cat_common.utils.AppUserUtil;
import com.xinlang.zly_xyx.company.bean.Company;
import com.xinlang.zly_xyx.company.service.ICompanyService;
import com.xinlang.zly_xyx.user.LoginAppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019-09-25
 */
@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private ICompanyService companyService;


    @PostMapping
    public Company save(@RequestBody Company company){
        company.setCreateTime(new Date());
        company.setEnabled(true);
        companyService.save(company);
        return company;
    }

    @PutMapping
    public Company update(@RequestBody Company company){
        company.setUpdateTime(new Date());
        companyService.update(company);
        return company;
    }

    @GetMapping("/all")
    public List<Company> findAll(){
        return companyService.findAll();
    }

    @GetMapping("/{deptCode}")
    public Company findByDeptCode(@PathVariable String deptCode){
        return companyService.findByDeptCode(deptCode);
    }

    /**
     * 删除公司及公司用户中间表信息
     * @param deptCode
     */
    @DeleteMapping("/deptCode")
    public void delete(@PathVariable String deptCode){
        companyService.delete(deptCode);
    }



}
