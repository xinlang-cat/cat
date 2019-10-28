package com.xinlang.zly_xyx.company.controller;

import com.xinlang.zly_xyx.cat_common.utils.AppUserUtil;
import com.xinlang.zly_xyx.company.bean.Company;
import com.xinlang.zly_xyx.company.service.ICompanyService;
import com.xinlang.zly_xyx.user.LoginAppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019-09-25
 */
@RestController
public class CompanyController {
    @Autowired
    private ICompanyService companyService;

    @PostMapping("/company")
    public Company save(@RequestBody Company company){
        companyService.save(company);
        return company;
    }

    @GetMapping("/company")
    public List<Company> getAll(){
        return companyService.getAll();
    }



    @GetMapping("/user")
    public LoginAppUser user(){
        return AppUserUtil.getLoginAppUser();
    }
}
