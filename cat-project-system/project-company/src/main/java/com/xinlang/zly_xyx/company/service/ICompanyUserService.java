package com.xinlang.zly_xyx.company.service;

import com.xinlang.zly_xyx.cat_common.service.IBaseService;
import com.xinlang.zly_xyx.company.bean.CompanyUser;

import java.util.List;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019-11-02
 */
public interface ICompanyUserService extends IBaseService<CompanyUser> {

    void save(CompanyUser companyUser);
    void update(CompanyUser companyUser);
    CompanyUser findByUserId(Integer userId);
    List<CompanyUser> findByDeptCode(String deptCode);
    void deleteByUserId(Integer userId);
    void deleteByDeptCode(String deptCode);
}
