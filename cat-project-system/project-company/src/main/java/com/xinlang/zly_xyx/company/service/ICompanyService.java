package com.xinlang.zly_xyx.company.service;

import com.xinlang.bean.company.Company;
import com.xinlang.zly_xyx.common.Page;

import java.util.List;
import java.util.Map;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019-09-25
 */
public interface ICompanyService {

    void save(Company company);
    void update(Company company);
    void delete(String DeptCode);
    Company findByDeptCode(String DeptCode);
    Company findByUserId(Integer userId);
    List<Company> findAll();
    Page<Company> findByParams(Map<String,Object> params);
    Page<Company> link(Map<String,Object> params);

    Company findByName(String name);
}
