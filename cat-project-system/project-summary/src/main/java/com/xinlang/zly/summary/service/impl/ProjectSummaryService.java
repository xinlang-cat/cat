package com.xinlang.zly.summary.service.impl;

import com.xinlang.zly.summary.bean.ProjectSummary;
import com.xinlang.zly.summary.mapper.ProjectSummaryMapper;
import com.xinlang.zly.summary.service.IProjectSummaryService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019-11-11
 */
@Service
public class ProjectSummaryService implements IProjectSummaryService {

    @Autowired
    private ProjectSummaryMapper projectSummaryMapper;

    @Override
    public void save(ProjectSummary projectSummary) {
        projectSummary.setCreateTime(new Date());
        projectSummaryMapper.insertSelective(projectSummary);
    }

    @Override
    public void update(ProjectSummary projectSummary) {
        projectSummary.setUpdateTime(new Date());
        projectSummaryMapper.updateByPrimaryKeySelective(projectSummary);
    }

    @Override
    public ProjectSummary findByUserIdAndItemId(Integer userId, Integer itemId) {
        Example example = new Example(ProjectSummary.class);
        example.createCriteria().andEqualTo("userId",userId).andEqualTo("itemId",itemId);
        List<ProjectSummary> list = projectSummaryMapper.selectByExample(example);
        return list.isEmpty()?null:list.get(0);
    }

    @Override
    public List<ProjectSummary> findByUserId(Integer userId) {
        Example example = new Example(ProjectSummary.class);
        example.createCriteria().andEqualTo("userId",userId);
        example.orderBy("createTime").desc();
        return projectSummaryMapper.selectByExample(example);
    }

    @Override
    public List<ProjectSummary> findByItemIdAndUserType(Integer itemId, String userType) {
        Example example = new Example(ProjectSummary.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("itemId",itemId);
        if(!StringUtils.isBlank(userType)){
            criteria.andEqualTo("userType",userType);
        }
        return projectSummaryMapper.selectByExample(example);
    }

    @Override
    public void delete(Integer id) {
        projectSummaryMapper.deleteByPrimaryKey(id);
    }
}
