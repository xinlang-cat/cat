package com.xinlang.zly_xyx.company.mapper;

import com.xinlang.bean.company.Company;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019-09-23
 */
@Mapper
@Repository("companyMapper")
public interface CompanyMapper extends tk.mybatis.mapper.common.Mapper<Company> {

}
