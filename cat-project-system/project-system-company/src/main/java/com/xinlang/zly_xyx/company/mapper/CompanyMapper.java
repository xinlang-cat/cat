package com.xinlang.zly_xyx.company.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import  com.xinlang.zly_xyx.company.bean.Company;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019-09-23
 */
@Mapper
@Repository("companyMapper")
public interface CompanyMapper extends tk.mybatis.mapper.common.Mapper<Company> {

}
