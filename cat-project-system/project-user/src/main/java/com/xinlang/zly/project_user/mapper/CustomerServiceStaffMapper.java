package com.xinlang.zly.project_user.mapper;

import com.xinlang.zly_xyx.user.CustomerServiceStaff;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository("customerServiceStaffMapper")
public interface CustomerServiceStaffMapper extends tk.mybatis.mapper.common.Mapper<CustomerServiceStaff> {
}
