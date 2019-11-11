package com.xinlang.zly.summary.service;

import com.xinlang.zly.summary.bean.ProjectSummary;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019-11-11
 */
public interface IProjectSummaryService {
    void save(ProjectSummary projectSummary);
    void update(ProjectSummary projectSummary);
    ProjectSummary findByUserIdAndItemId(Integer userId,Integer itemId);
    void delete(Integer id);
}
