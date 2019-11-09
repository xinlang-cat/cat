package com.xinlang.zly.project_user.service.impl;

import com.xinlang.bean.project_user.ProjectUserSkill;
import com.xinlang.zly.project_user.mapper.ProjectUserSkillMapper;
import com.xinlang.zly.project_user.service.IProjectUserSkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019-11-09
 */
@Service
@Transactional
public class ProjectUserSkillService implements IProjectUserSkillService {

    @Autowired
    private ProjectUserSkillMapper projectUserSkillMapper;

    @Override
    public void save(ProjectUserSkill projectUserSkill) {
        Example example = new Example(ProjectUserSkill.class);
        example.createCriteria().andEqualTo("userId",projectUserSkill.getUserId());
        int count = projectUserSkillMapper.selectCountByExample(example);
        //每个人不超过3个
        if(count > 3){
            throw new IllegalArgumentException("标签数量已超");
        }
        projectUserSkillMapper.insert(projectUserSkill);
    }

    @Override
    public void update(ProjectUserSkill projectUserSkill) {
        Example example = new Example(ProjectUserSkill.class);
        example.createCriteria().andNotEqualTo("id",projectUserSkill.getId());
        projectUserSkillMapper.updateByExample(projectUserSkill,example);
    }

    @Override
    public List<ProjectUserSkill> findByUserId(Integer userId) {
        Example example = new Example(ProjectUserSkill.class);
        example.createCriteria().andEqualTo("userId",userId);
        return projectUserSkillMapper.selectByExample(example);
    }

    @Override
    public List<ProjectUserSkill> findByLabelSign(String labelSign,String userType) {
        Example example = new Example(ProjectUserSkill.class);
        example.createCriteria().andEqualTo("labelSign",labelSign).andEqualTo("userType",userType);
        return projectUserSkillMapper.selectByExample(example);
    }

    @Override
    public void delete(Integer id) {
        Example example = new Example(ProjectUserSkill.class);
        example.createCriteria().andNotEqualTo("id",id);
        projectUserSkillMapper.deleteByExample(example);
    }
}
