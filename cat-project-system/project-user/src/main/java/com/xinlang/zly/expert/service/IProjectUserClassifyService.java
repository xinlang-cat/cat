package com.xinlang.zly.expert.service;

import com.xinlang.zly.expert.bean.ProjectUserClassify;

import java.util.List;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019-11-05
 */
public interface IProjectUserClassifyService {
    void save(ProjectUserClassify projectUserClassify);
    void update(ProjectUserClassify projectUserClassify);
    List<ProjectUserClassify> findAllGroup();
    ProjectUserClassify findById(Integer id);
    void deleteById(Integer id);
}
