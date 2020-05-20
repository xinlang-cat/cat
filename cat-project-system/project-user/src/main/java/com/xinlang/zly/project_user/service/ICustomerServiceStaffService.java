package com.xinlang.zly.project_user.service;

import com.xinlang.zly_xyx.cat_common.service.IBaseService;
import com.xinlang.zly_xyx.user.CustomerServiceStaff;

import java.util.List;

public interface ICustomerServiceStaffService extends IBaseService<CustomerServiceStaff> {

    List<CustomerServiceStaff> findAll();
}
