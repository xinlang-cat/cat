package com.xinlang.zly.project_user.service;

import com.xinlang.zly.project_user.bean.ProjectUserClassify;

import java.util.List;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019-11-05
 */
public interface IProjectUserClassifyService {
    void save(ProjectUserClassify projectUserClassify);
    void update(ProjectUserClassify projectUserClassify);
    List<ProjectUserClassify> findAllGroup();
    List<ProjectUserClassify> findGroupByTechnicalExpertise(String technicalExpertise);
    List<ProjectUserClassify> findByUserId(Integer userId);
    ProjectUserClassify findById(Integer id);
    void deleteById(Integer id);
}
