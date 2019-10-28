package com.xinlang.zly_xyx.company.service.impl;

import com.xinlang.zly_xyx.company.bean.Company;
import com.xinlang.zly_xyx.company.mapper.CompanyMapper;
import com.xinlang.zly_xyx.company.service.ICompanyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019-09-25
 */
@Slf4j
@Service
public class CompanyService implements ICompanyService {
    @Autowired
    private CompanyMapper companyMapper;

    @Override
    public int save(Company company){
        log.info("添加公司",company);
        return companyMapper.insert(company);
    }

    @Override
    public List<Company> getAll(){
        return companyMapper.selectAll();
    }
}
