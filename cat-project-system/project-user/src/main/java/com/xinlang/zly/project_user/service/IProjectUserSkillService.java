package com.xinlang.zly.project_user.service;

import com.xinlang.bean.project_user.ProjectUserSkill;

import java.util.List;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019-11-09
 */
public interface IProjectUserSkillService {
    void save(ProjectUserSkill projectUserSkill);
    void update(ProjectUserSkill projectUserSkill);
    List<ProjectUserSkill> findByUserId(Integer userId);
    List<ProjectUserSkill> findByLabelSign(String labelSign,String userType);
    void delete(Integer userId);
}
