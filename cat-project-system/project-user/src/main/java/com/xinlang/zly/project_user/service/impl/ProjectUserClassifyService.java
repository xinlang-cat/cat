package com.xinlang.zly.project_user.service.impl;

import com.xinlang.bean.project_user.ProjectUser;
import com.xinlang.bean.project_user.ProjectUserClassify;
import com.xinlang.zly.project_user.mapper.ProjectUserClassifyMapper;
import com.xinlang.zly.project_user.mapper.ProjectUserMapper;
import com.xinlang.zly.project_user.service.IProjectUserClassifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019-11-05
 */
@Service
@Transactional
public class ProjectUserClassifyService implements IProjectUserClassifyService {

    @Autowired
    private ProjectUserClassifyMapper projectUserClassifyMapper;
    @Autowired
    private ProjectUserMapper projectUserMapper;

    @Override
    public void save(ProjectUserClassify projectUserClassify) {
        projectUserClassifyMapper.insert(projectUserClassify);
    }

    @Override
    public void update(ProjectUserClassify projectUserClassify) {
        Example example = new Example(ProjectUserClassify.class);
        example.createCriteria().andEqualTo("id",projectUserClassify.getId());
        projectUserClassifyMapper.updateByExample(projectUserClassify,example);
    }

    @Override
    public List<ProjectUserClassify> findAllGroup() {
        Example example = new Example(ProjectUserClassify.class);
        example.createCriteria().andEqualTo("pid",0);
        return projectUserClassifyMapper.selectByExample(example);
    }

    @Override
    public List<ProjectUserClassify> findGroupByTechnicalExpertise(String technicalExpertise) {
        Example example = new Example(ProjectUserClassify.class);
        example.createCriteria().andEqualTo("technicalExpertise",technicalExpertise).andEqualTo("pid",0);
        return projectUserClassifyMapper.selectByExample(example);
    }

    @Override
    public List<ProjectUserClassify> findByUserId(Integer userId) {
        Example example = new Example(ProjectUserClassify.class);
        example.createCriteria().andEqualTo("userId",userId);
        return projectUserClassifyMapper.selectByExample(example);
    }

    @Override
    public ProjectUserClassify findById(Integer id) {
        Example example = new Example(ProjectUserClassify.class);
        example.createCriteria().andEqualTo("id",id);
        ProjectUserClassify projectUserClassify = projectUserClassifyMapper.selectOneByExample(example);
        Set<String> set = new HashSet<>();
        if(!projectUserClassify.getPid().equals(0)){
            set.add(projectUserClassify.getIdCard());
            List<ProjectUser> list = findProjectUserByIDCard(set);
            projectUserClassify.setProjectUser(list.get(0));
            return projectUserClassify;
        }
        example.clear();
        example.createCriteria().andEqualTo("pid",projectUserClassify.getId());
        List<ProjectUserClassify> list = projectUserClassifyMapper.selectByExample(example);
        if(!list.isEmpty()){
            list.forEach(puc ->{
                set.add(puc.getIdCard());
            });
            List<ProjectUser> pus = findProjectUserByIDCard(set);
            list.forEach(puc1->{
                pus.forEach(pu->{
                    if(puc1.getIdCard().equals(pu.getIdCard())){
                        puc1.setProjectUser(pu);
                    }
                });
            });
            projectUserClassify.setChild(list);
        }
        return projectUserClassify;
    }

    @Override
    public void deleteById(Integer id) {
        Example example = new Example(ProjectUserClassify.class);
        example.createCriteria().andEqualTo("id",id);
        ProjectUserClassify projectUserClassify = projectUserClassifyMapper.selectOneByExample(example);
        //有子集就删除子集
        if(projectUserClassify.getPid().equals(0)){
            example.clear();
            example.createCriteria().andEqualTo("pid",projectUserClassify.getId());
            projectUserClassifyMapper.deleteByExample(example);
        }
        example.clear();
        example.createCriteria().andEqualTo("id",projectUserClassify.getId());
        projectUserClassifyMapper.deleteByExample(example);

    }

    private List<ProjectUser> findProjectUserByIDCard(Set<String> IDCards){
        Example example = new Example(ProjectUser.class);
        example.createCriteria().andIn("idCard",IDCards);
        return projectUserMapper.selectByExample(example);
    }
}
