package com.xinlang.zly.project_user.service.impl;

import com.xinlang.bean.project_user.ProjectUserDomain;
import com.xinlang.zly.project_user.mapper.ProjectUserDomainMapper;
import com.xinlang.zly.project_user.service.IProjectUserDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019-11-09
 */
@Service
@Transactional
public class ProjectUserDomainService implements IProjectUserDomainService {

    @Autowired
    private ProjectUserDomainMapper projectUserDomainMapper;

    @Override
    public void save(ProjectUserDomain projectUserDomain) {
        Example example = new Example(ProjectUserDomain.class);
        Example.Criteria criteria  = example.createCriteria();
        criteria.andEqualTo("userId",projectUserDomain.getUserId());
        int count = projectUserDomainMapper.selectCountByExample(example);
        //每个人不超过3个
        if(count > 3){
            throw new IllegalArgumentException("标签数量已超");
        }
        criteria.andEqualTo("labelSign",projectUserDomain.getLabelSign());
        criteria.andEqualTo("userType",projectUserDomain.getUserType());
        int count1 = projectUserDomainMapper.selectCountByExample(example);
        if(count1<1){
            projectUserDomainMapper.insertSelective(projectUserDomain);
        }
    }

    @Override
    public void update(ProjectUserDomain projectUserDomain) {
        Example example = new Example(ProjectUserDomain.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId",projectUserDomain.getUserId());
        criteria.andEqualTo("labelSign",projectUserDomain.getLabelSign());
        ProjectUserDomain pud = projectUserDomainMapper.selectOneByExample(example);
        if(pud != null){
            projectUserDomain = pud;
        }else {
            projectUserDomainMapper.updateByPrimaryKeySelective(projectUserDomain);
        }
    }

    @Override
    public List<ProjectUserDomain> findByUserId(Integer userId) {
        Example example = new Example(ProjectUserDomain.class);
        example.createCriteria().andEqualTo("userId",userId);
        return projectUserDomainMapper.selectByExample(example);
    }

    @Override
    public List<ProjectUserDomain> findByLabelSign(String labelSign,String userType) {
        Example example = new Example(ProjectUserDomain.class);
        example.createCriteria().andEqualTo("labelSign",labelSign).andEqualTo("userType",userType);
        return projectUserDomainMapper.selectByExample(example);
    }

    @Override
    public void delete(Integer id) {
        projectUserDomainMapper.deleteByPrimaryKey(id);
    }
}
