package com.xinlang.zly_xyx.company.service.impl;

import com.xinlang.zly_xyx.company.bean.CompanyUser;
import com.xinlang.zly_xyx.company.mapper.CompanyUserMapper;
import com.xinlang.zly_xyx.company.service.ICompanyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019-11-02
 */
@Service
@Transactional
public class CompanyUserService implements ICompanyUserService {

    @Autowired
    private CompanyUserMapper companyUserMapper;

    @Override
    public void save(CompanyUser companyUser) {
        Example example = new Example(CompanyUser.class);
        example.createCriteria().andEqualTo("userId",companyUser.getUserId()).andEqualTo("deptCode",companyUser.getDeptCode());
        if(companyUserMapper.selectByExample(example) == null){
            throw new IllegalArgumentException("该用户已绑定其他组织!");
        }
       companyUserMapper.insert(companyUser);
    }

    @Override
    public void update(CompanyUser companyUser) {
        Example example = new Example(CompanyUser.class);
        example.createCriteria().andEqualTo("id",companyUser.getId());
        companyUserMapper.updateByExampleSelective(companyUser,example);
    }

    @Override
    public CompanyUser findByUserId(Integer userId) {
        Example example = new Example(CompanyUser.class);
        example.createCriteria().andEqualTo("userId",userId);
        List<CompanyUser> list = companyUserMapper.selectByExample(example);
        return list == null?null:list.get(0);
    }

    @Override
    public List<CompanyUser> findByDeptCode(String deptCode) {
        Example example = new Example(CompanyUser.class);
        example.createCriteria().andEqualTo("deptCode",deptCode);
        return companyUserMapper.selectByExample(example);
    }

    @Override
    public void deleteByUserId(Integer userId) {
        Example example = new Example(CompanyUser.class);
        example.createCriteria().andEqualTo("userId",userId);
        companyUserMapper.deleteByExample(example);
    }

    @Override
    public void deleteByDeptCode(String deptCode) {
        Example example = new Example(CompanyUser.class);
        example.createCriteria().andEqualTo("deptCode",deptCode);
        companyUserMapper.deleteByExample(example);
    }
}
