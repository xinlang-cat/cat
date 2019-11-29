package com.xinlang.zly.project_user.service;

import com.xinlang.bean.project_user.ProjectUser;
import com.xinlang.zly_xyx.common.Page;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019-11-05
 */
public interface IProjectUserService {
    void save(ProjectUser projectUser);
    void update(ProjectUser projectUser);
    List<ProjectUser> findAll();
    List<ProjectUser> findByUserType(String userType);
    List<ProjectUser> findByUserId(Integer userId);
    List<ProjectUser> findByUserIds(Set<Integer> userIds);
    void delete(Integer id);
    Page<ProjectUser> findByParams(Map<String,Object> params);
    Page<ProjectUser> link(Map<String,Object> params);
}
