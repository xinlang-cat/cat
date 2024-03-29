package com.xinlang.zly.project_user.service.impl;

import com.xinlang.bean.project_user.ProjectUserItem;
import com.xinlang.zly.project_user.mapper.ProjectUserItemMapper;
import com.xinlang.zly.project_user.service.IProjectUserItemService;
import com.xinlang.zly_xyx.cat_common.service.impl.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019-11-09
 */
@Service
public class ProjectUserItemService extends BaseService<ProjectUserItem> implements IProjectUserItemService {

    @Autowired
    private ProjectUserItemMapper projectUserItemMapper;
    @Override
    public void save(ProjectUserItem projectUserItem) {
        projectUserItem.setCreateTime(new Date());
        projectUserItemMapper.insertSelective(projectUserItem);
    }

    @Override
    public List<ProjectUserItem> findByUserIdAndUserType(Integer userId, String userType) {
        Example example = new Example(ProjectUserItem.class);
        example.createCriteria().andEqualTo("userId",userId)
                .andEqualTo("userType",userType);
        example.orderBy("createTime").asc();
        return projectUserItemMapper.selectByExample(example);
    }


    @Override
    public List<ProjectUserItem> findByItemIdAndUserType(Integer itemId,String userType) {
        Example example = new Example(ProjectUserItem.class);
        example.createCriteria().andEqualTo("itemId",itemId).andEqualTo("userType",userType);
        return projectUserItemMapper.selectByExample(example);
    }


    @Override
    public List<ProjectUserItem> findByLabelSign(String labelSign, String userType) {
        Example example = new Example(ProjectUserItem.class);
        example.createCriteria().andEqualTo("labelSign",labelSign).andEqualTo("userType",userType);
        return projectUserItemMapper.selectByExample(example);
    }

    @Override
    public void delete(Integer id) {
        projectUserItemMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void randomDelete(Integer itemId, String labelSign, String userType) {
        Example example = new Example(ProjectUserItem.class);
        example.createCriteria().andEqualTo("itemId").andEqualTo("labelSign",labelSign).andEqualTo("userType",userType);
        List<ProjectUserItem> list = projectUserItemMapper.selectByExample(example);
        Random random = new Random();
        int index = random.nextInt(list.size());
        projectUserItemMapper.deleteByPrimaryKey(list.get(index).getId());
    }

}
