package com.xinlang.zly_xyx.company.mapper;

import com.xinlang.zly_xyx.company.bean.CompanyUser;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019-11-02
 */
@org.apache.ibatis.annotations.Mapper
@Repository("companyUserMapper")
public interface CompanyUserMapper extends Mapper<CompanyUser> {
}
