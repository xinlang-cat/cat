package com.xinlang.zly.summary.service;

import com.xinlang.zly.summary.bean.ProjectSummary;

import java.util.List;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019-11-11
 */
public interface IProjectSummaryService {
    void save(ProjectSummary projectSummary);
    void update(ProjectSummary projectSummary);
    ProjectSummary findByUserIdAndItemId(Integer userId,Integer itemId);
    List<ProjectSummary> findByUserId(Integer userId);
    List<ProjectSummary> findByItemIdAndUserType(Integer itemId,String userType);
    void delete(Integer id);
}
