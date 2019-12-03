package com.xinlang.zly_xyx.company.service.impl;

import com.xinlang.bean.company.Company;
import com.xinlang.zly_xyx.cat_common.utils.BeanUtils;
import com.xinlang.zly_xyx.cat_common.utils.ExampleUtil;
import com.xinlang.zly_xyx.cat_common.utils.RowBoundsUtil;
import com.xinlang.zly_xyx.common.Page;
import com.xinlang.zly_xyx.company.bean.CompanyUser;
import com.xinlang.zly_xyx.company.mapper.CompanyMapper;
import com.xinlang.zly_xyx.company.mapper.CompanyUserMapper;
import com.xinlang.zly_xyx.company.service.ICompanyService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019-09-25
 */
@Service
@Transactional
public class CompanyService implements ICompanyService {

    @Autowired
    private CompanyMapper companyMapper;
    @Autowired
    private CompanyUserMapper companyUserMapper;

    @Override
    public void save(Company company){
        companyMapper.insertSelective(company);
    }

    @Override
    public void update(Company company) {
        Example example = new Example(Company.class);
        example.createCriteria().andEqualTo("deptCode",company.getDeptCode());
        companyMapper.updateByExampleSelective(company,example);
    }

    @Override
    public void delete(String deptCode) {
        Example example = new Example(Company.class);
        example.createCriteria().andEqualTo("deptCode",deptCode);
        companyMapper.deleteByExample(example);
        Example example1 = new Example(CompanyUser.class);
        example1.createCriteria().andEqualTo("deptCode",deptCode);
        companyUserMapper.deleteByExample(example);
    }

    @Override
    public Company findByDeptCode(String deptCode) {
        Example example = new Example(Company.class);
        example.createCriteria().andEqualTo("deptCode",deptCode);
        List<Company> list = companyMapper.selectByExample(example);
        if(!list.isEmpty()){
            return list.get(0);
        }
        return null;
    }

    @Override
    public Company findByUserId(Integer userId) {
        Example example = new Example(CompanyUser.class);
        example.createCriteria().andEqualTo("userId",userId);
        CompanyUser companyUser  = companyUserMapper.selectOneByExample(example);
        if(companyUser != null){
            Example example1 = new Example(Company.class);
            example1.createCriteria().andEqualTo("deptCode",companyUser.getDeptCode());
            return companyMapper.selectOneByExample(example1);
        }
        return null;
    }

    @Override
    public List<Company> findAll(){
        return companyMapper.selectAll();
    }

    @Override
    public Page<Company> findByParams(Map<String,Object> params) {
        Company company = BeanUtils.toBean(params,Company.class);
        List<Company> companys = Collections.emptyList();
        int total = companyMapper.selectCount(company);
        if(total>0){
            RowBounds rowBounds = RowBoundsUtil.getRowBounds(params);
            companys = companyMapper.selectByRowBounds(company,rowBounds);
        }
        return new Page<>(total,companys);
    }
    @Override
    public Page<Company> link(Map<String,Object> params){
        Example example = ExampleUtil.getLinkExample(params,Company.class,"%","%");
       // example.orderBy("id").asc();//排序
        List<Company> companys = Collections.emptyList();
        int total = companyMapper.selectCountByExample(example);
        if(total>0){
            //RowBounds和setOrderByClause使用一种即可，RowBounds性能更佳
            //example.setOrderByClause("id limit " + params.get("start") + "," + params.get("length"));
            RowBounds rowBounds = RowBoundsUtil.getRowBounds(params);
            companys = companyMapper.selectByExampleAndRowBounds(example,rowBounds);
        }
        return new Page<>(total,companys);
    }

}
