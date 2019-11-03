package com.xinlang.zly_xyx.company.service;

import com.xinlang.zly_xyx.company.bean.Company;

import java.util.List;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019-09-25
 */
public interface ICompanyService {

    void save(Company company);
    void update(Company company);
    void delete(String DeptCode);
    Company findByDeptCode(String DeptCode);
    List<Company> findAll();
}