package com.xinlang.zly.project_user.service.impl;

import com.xinlang.bean.project_user.ProjectUserItem;
import com.xinlang.zly.project_user.mapper.ProjectUserItemMapper;
import com.xinlang.zly.project_user.service.IProjectUserItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019-11-09
 */
@Service
public class ProjectUserItemService implements IProjectUserItemService {

    @Autowired
    private ProjectUserItemMapper projectUserItemMapper;
    @Override
    public void save(ProjectUserItem projectUserItem) {
        projectUserItemMapper.insert(projectUserItem);
    }

    @Override
    public Integer countByUserId(Integer userId) {
        Example example = new Example(ProjectUserItem.class);
        example.createCriteria().andEqualTo("userId",userId);
        return projectUserItemMapper.selectCountByExample(example);
    }

    @Override
    public List<ProjectUserItem> findByItemIdAndUserType(Integer itemId,String userType) {
        Example example = new Example(ProjectUserItem.class);
        example.createCriteria().andEqualTo("itemId",itemId).andEqualTo("userType",userType);
        return projectUserItemMapper.selectByExample(example);
    }

    @Override
    public List<ProjectUserItem> findByUserId(Integer userId) {
        Example example = new Example(ProjectUserItem.class);
        example.createCriteria().andEqualTo("userId",userId);
        return projectUserItemMapper.selectByExample(example);
    }

    @Override
    public List<ProjectUserItem> findByLabelSign(String labelSign, String userType) {
        Example example = new Example(ProjectUserItem.class);
        example.createCriteria().andEqualTo("labelSign",labelSign).andEqualTo("userType",userType);
        return projectUserItemMapper.selectByExample(example);
    }
}
