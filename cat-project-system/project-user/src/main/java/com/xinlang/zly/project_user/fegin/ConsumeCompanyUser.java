package com.xinlang.zly.project_user.fegin;

import com.xinlang.bean.company.CompanyUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("project-company")
public interface ConsumeCompanyUser {

    @PostMapping("/user")
    CompanyUser save(@RequestBody CompanyUser companyUser) ;
}
