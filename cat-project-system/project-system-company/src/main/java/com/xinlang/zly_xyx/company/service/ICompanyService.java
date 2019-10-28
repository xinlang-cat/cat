package com.xinlang.zly_xyx.company.service;

import com.xinlang.zly_xyx.company.bean.Company;

import java.util.List;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019-09-25
 */
public interface ICompanyService {

    int save(Company company);
    List<Company> getAll();
}
