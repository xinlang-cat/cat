package com.xinlang.zly.project_user.service.impl;

import com.xinlang.bean.project_user.ProjectUser;
import com.xinlang.zly.project_user.mapper.ProjectUserDomainMapper;
import com.xinlang.zly.project_user.mapper.ProjectUserMapper;
import com.xinlang.zly.project_user.mapper.ProjectUserSkillMapper;
import com.xinlang.zly.project_user.service.IProjectUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019-11-05
 */
@Service
@Transactional
public class ProjectUserService implements IProjectUserService {

    @Autowired
    private ProjectUserMapper projectUserMapper;


    @Override
    public void save(ProjectUser projectUser) {
        projectUser.setCreateTime(new Date());
        projectUserMapper.insertSelective(projectUser);
    }

    @Override
    public void update(ProjectUser projectUser) {
        projectUser.setUpdateTime(new Date());
        projectUserMapper.updateByPrimaryKeySelective(projectUser);
    }

    @Override
    public List<ProjectUser> findAll() {
        return projectUserMapper.selectAll();
    }

    @Override
    public List<ProjectUser> findByUserType(String userType) {
        Example example = new Example(ProjectUser.class);
        example.createCriteria().andEqualTo("userType",userType).andEqualTo("enable",true);
        return projectUserMapper.selectByExample(example);
    }


    @Override
    public List<ProjectUser> findByUserId(Integer userId) {
        Example example = new Example(ProjectUser.class);
        example.createCriteria().andEqualTo("userId",userId).andEqualTo("enable",true);
        return projectUserMapper.selectByExample(example);
    }

    @Override
    public List<ProjectUser> findByUserIds(Set<Integer> userIds) {
        Example example = new Example(ProjectUser.class);
        example.createCriteria().andIn("userId",userIds).andEqualTo("enable",true);
        return projectUserMapper.selectByExample(example);
    }

    @Override
    public void delete(Integer id) {
        projectUserMapper.deleteByPrimaryKey(id);
    }
}
