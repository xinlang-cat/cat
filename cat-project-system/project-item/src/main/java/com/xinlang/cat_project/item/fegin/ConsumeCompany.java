package com.xinlang.cat_project.item.fegin;

import com.xinlang.bean.company.Company;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("project-company")
public interface ConsumeCompany {

    @GetMapping("/company-anon/{userId}")
    Company findByUserId(@PathVariable Integer userId);
}
