package com.xinlang.zly.project_user.service;

import com.xinlang.bean.project_user.ProjectUserItem;

import java.util.List;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019-11-09
 */
public interface IProjectUserItemService {
    void save(ProjectUserItem projectUserItem);
    List<ProjectUserItem> findByUserIdAndUserType(Integer userId,String userType);
    List<ProjectUserItem> findByItemIdAndUserType(Integer itemId,String userType);
    List<ProjectUserItem> findByLabelSign(String labelSign,String userType);
    void delete(Integer id);
    void randomDelete(Integer itemId,String labelSign,String userType);
}
