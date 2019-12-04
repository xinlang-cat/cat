package com.xinlang.zly.project_user.service.impl;

import com.xinlang.bean.project_user.ProjectUser;
import com.xinlang.zly.project_user.mapper.ProjectUserMapper;
import com.xinlang.zly.project_user.service.IProjectUserService;
import com.xinlang.zly_xyx.cat_common.utils.BeanUtils;
import com.xinlang.zly_xyx.cat_common.utils.ExampleUtil;
import com.xinlang.zly_xyx.cat_common.utils.RowBoundsUtil;
import com.xinlang.zly_xyx.common.Page;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.*;

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


    @Override
    public Page<ProjectUser> findByParams(Map<String,Object> params) {
        ProjectUser ProjectUser = BeanUtils.toBean(params,ProjectUser.class);
        List<ProjectUser> ProjectUsers = Collections.emptyList();
        int total = projectUserMapper.selectCount(ProjectUser);
        if(total>0){
            RowBounds rowBounds = RowBoundsUtil.getRowBounds(params);
            ProjectUsers = projectUserMapper.selectByRowBounds(ProjectUser,rowBounds);
        }
        return new Page<>(total,ProjectUsers);
    }
    @Override
    public Page<ProjectUser> link(Map<String,Object> params){
        Example example = ExampleUtil.getLinkExample(params,ProjectUser.class,"%","%");
        // example.orderBy("id").asc();//排序
        List<ProjectUser> ProjectUsers = Collections.emptyList();
        int total = projectUserMapper.selectCountByExample(example);
        if(total>0){
            //RowBounds和setOrderByClause使用一种即可，RowBounds性能更佳
            //example.setOrderByClause("id limit " + params.get("start") + "," + params.get("length"));
            RowBounds rowBounds = RowBoundsUtil.getRowBounds(params);
            ProjectUsers = projectUserMapper.selectByExampleAndRowBounds(example,rowBounds);
        }
        return new Page<>(total,ProjectUsers);
    }
}
