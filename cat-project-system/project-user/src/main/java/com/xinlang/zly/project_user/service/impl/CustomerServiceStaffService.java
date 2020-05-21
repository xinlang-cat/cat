package com.xinlang.zly.project_user.service.impl;

import com.xinlang.zly.project_user.mapper.CustomerServiceStaffMapper;
import com.xinlang.zly.project_user.service.ICustomerServiceStaffService;
import com.xinlang.zly_xyx.cat_common.service.impl.BaseService;
import com.xinlang.zly_xyx.user.CustomerServiceStaff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CustomerServiceStaffService extends BaseService<CustomerServiceStaff> implements ICustomerServiceStaffService {
    @Autowired private CustomerServiceStaffMapper customerServiceStaffMapper;

    @Override
    public List<CustomerServiceStaff> findAll() {
        return customerServiceStaffMapper.selectAll();
    }
}
