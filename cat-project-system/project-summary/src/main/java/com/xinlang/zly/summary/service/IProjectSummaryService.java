package com.xinlang.zly.summary.service;

import com.xinlang.zly.summary.bean.ProjectSummary;
import com.xinlang.zly_xyx.common.Page;

import java.util.List;
import java.util.Map;

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
    Page<ProjectSummary> findByParams(Map<String,Object> params);
    Page<ProjectSummary> link(Map<String,Object> params);
}
