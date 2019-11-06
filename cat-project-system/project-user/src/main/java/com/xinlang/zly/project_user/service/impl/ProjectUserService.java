package com.xinlang.zly.project_user.service.impl;

import com.xinlang.zly.project_user.bean.ProjectUser;
import com.xinlang.zly.project_user.bean.ProjectUserClassify;
import com.xinlang.zly.project_user.mapper.ProjectUserClassifyMapper;
import com.xinlang.zly.project_user.mapper.ProjectUserMapper;
import com.xinlang.zly.project_user.service.IProjectUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019-11-05
 */
@Service
@Transactional
public class ProjectUserService implements IProjectUserService {

    @Autowired
    private ProjectUserMapper projectUserMapper;
    @Autowired
    private ProjectUserClassifyMapper projectUserClassifyMapper;

    @Override
    public void save(ProjectUser projectUser) {
        projectUser.setCreateTime(new Date());
        projectUserMapper.insert(projectUser);
    }

    @Override
    public void update(ProjectUser projectUser) {
        projectUser.setUpdateTime(new Date());
        Example example = new Example(ProjectUser.class);
        example.createCriteria().andEqualTo("id",projectUser.getId());
        projectUserMapper.updateByExample(projectUser,example);
    }

    @Override
    public List<ProjectUser> findAll() {
        return projectUserMapper.selectAll();
    }

    @Override
    public List<ProjectUser> findByUserType(String userType) {
        Example example = new Example(ProjectUser.class);
        example.createCriteria().andEqualTo("userType",userType);
        return projectUserMapper.selectByExample(example);
    }

    @Override
    public ProjectUser findByUserId(Integer userId) {
        Example example = new Example(ProjectUser.class);
        example.createCriteria().andEqualTo("userId",userId);
        List<ProjectUser> list = projectUserMapper.selectByExample(example);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public void deleteByUserId(Integer userId) {
        Example example = new Example(ProjectUser.class);
        example.createCriteria().andEqualTo("userId",userId);
        projectUserMapper.deleteByExample(example);
        //删除专家用户分组信息
        Example example1 = new Example(ProjectUserClassify.class);
        example1.createCriteria().andEqualTo("userId",userId);
        projectUserClassifyMapper.deleteByExample(example1);
    }
}
