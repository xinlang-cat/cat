package com.xinlang.zly.summary.service.impl;

import com.xinlang.zly.summary.bean.ProjectSummary;
import com.xinlang.zly.summary.mapper.ProjectSummaryMapper;
import com.xinlang.zly.summary.service.IProjectSummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

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
        projectSummaryMapper.insert(projectSummary);
    }

    @Override
    public void update(ProjectSummary projectSummary) {
        projectSummaryMapper.updateByPrimaryKey(projectSummary);
    }

    @Override
    public ProjectSummary findByUserIdAndItemId(Integer userId, Integer itemId) {
        Example example = new Example(ProjectSummary.class);
        example.createCriteria().andEqualTo("userId",userId).andEqualTo("itemId",itemId);
        List<ProjectSummary> list = projectSummaryMapper.selectByExample(example);
        return list.isEmpty()?null:list.get(0);
    }

    @Override
    public void delete(Integer id) {
        projectSummaryMapper.deleteByPrimaryKey(id);
    }
}
