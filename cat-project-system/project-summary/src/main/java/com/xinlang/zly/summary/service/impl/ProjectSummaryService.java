package com.xinlang.zly.summary.service.impl;

import com.xinlang.bean.utils.BeanUtils;
import com.xinlang.bean.utils.ExampleUtil;
import com.xinlang.bean.utils.RowBoundsUtil;
import com.xinlang.zly.summary.bean.ProjectSummary;
import com.xinlang.zly.summary.mapper.ProjectSummaryMapper;
import com.xinlang.zly.summary.service.IProjectSummaryService;
import com.xinlang.zly_xyx.cat_common.utils.AppUserUtil;
import com.xinlang.zly_xyx.common.Page;
import com.xinlang.zly_xyx.user.AppUser;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
        AppUser appUser = AppUserUtil.getLoginAppUser();
        projectSummary.setUserId(appUser.getId().intValue());
        projectSummary.setName(appUser.getNickname());
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

    @Override
    public Page<ProjectSummary> findByParams(Map<String,Object> params) {
        ProjectSummary ProjectSummary = BeanUtils.toBean(params,ProjectSummary.class);
        List<ProjectSummary> ProjectSummarys = Collections.emptyList();
        int total = projectSummaryMapper.selectCount(ProjectSummary);
        if(total>0){
            RowBounds rowBounds = RowBoundsUtil.getRowBounds(params);
            ProjectSummarys = projectSummaryMapper.selectByRowBounds(ProjectSummary,rowBounds);
        }
        return new Page<>(total,ProjectSummarys);
    }
    @Override
    public Page<ProjectSummary> link(Map<String,Object> params){
        Example example = ExampleUtil.getLinkExample(params,ProjectSummary.class,"%","%");
        // example.orderBy("id").asc();//排序
        List<ProjectSummary> ProjectSummarys = Collections.emptyList();
        int total = projectSummaryMapper.selectCountByExample( example);
        if(total>0){
            //RowBounds和setOrderByClause使用一种即可，RowBounds性能更佳
            //example.setOrderByClause("id limit " + params.get("start") + "," + params.get("length"));
            RowBounds rowBounds = RowBoundsUtil.getRowBounds(params);
            ProjectSummarys = projectSummaryMapper.selectByExampleAndRowBounds(example,rowBounds);
        }
        return new Page<>(total,ProjectSummarys);
    }

}
