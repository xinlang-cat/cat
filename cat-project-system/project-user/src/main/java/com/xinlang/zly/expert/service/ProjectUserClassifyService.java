package com.xinlang.zly.expert.service;

import com.xinlang.zly.expert.bean.ProjectUserClassify;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019-11-05
 */
public interface ProjectUserClassifyService {
    void save(ProjectUserClassify projectUserClassify);
    void update(ProjectUserClassify projectUserClassify);
    void deleteById(Integer id);
}
