package com.xinlang.zly.project_user.service;

import com.xinlang.bean.project_user.ProjectUserDomain;
import com.xinlang.bean.project_user.ProjectUserSkill;

import java.util.List;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019-11-09
 */
public interface IProjectUserDomainService {
    void save(ProjectUserDomain projectUserDomain);
    void update(ProjectUserDomain projectUserDomain);
    List<ProjectUserDomain> findByUserId(Integer userId);
    List<ProjectUserDomain> findByLabelSign(String labelSign, String userType);
    void delete(Integer userId);
}
