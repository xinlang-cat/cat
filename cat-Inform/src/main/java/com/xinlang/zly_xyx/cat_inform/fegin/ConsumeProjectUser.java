package com.xinlang.zly_xyx.cat_inform.fegin;

import com.xinlang.zly_xyx.user.CustomerServiceStaff;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019-12-04
 */
@FeignClient("project-user")
public interface ConsumeProjectUser {
    @GetMapping("/customer-service-staff/all")
    List<CustomerServiceStaff> findCustomerServiceStaffs();
}
