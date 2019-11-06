package com.xinlang.zly.project_user.controller;

import com.xinlang.zly.project_user.bean.ProjectUserClassify;
import com.xinlang.zly.project_user.service.IProjectUserClassifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019-11-05
 */
@RestController
@RequestMapping("/projectUserClassify")
public class ProjectUserClassifyController {

    @Autowired
    private IProjectUserClassifyService projectUserClassifyService;


    @PostMapping
    public ProjectUserClassify save(@RequestBody ProjectUserClassify projectUserClassify){
        projectUserClassifyService.save(projectUserClassify);
        return projectUserClassify;
    }


    @PutMapping
    public ProjectUserClassify update(@RequestBody ProjectUserClassify projectUserClassify){
        projectUserClassifyService.update(projectUserClassify);
        return projectUserClassify;
    }

    /**
     * @return 获取所有分组,不包含成员
     */
    @GetMapping("/group")
    public List<ProjectUserClassify> findAllGroup(){
        return projectUserClassifyService.findAllGroup();
    }

    /**
     *
     * @param id 根据id获取组所有成员或单个成员，id为组id获取整个组的成员，id为成员
     * @return 单个或一个组的成员
     */
    @GetMapping("/{id}")
    public ProjectUserClassify findById(Integer id){
        return projectUserClassifyService.findById(id);
    }

    /**
     * @param id 根据id删除组或组成员
     */
    @DeleteMapping("/{id}")
    public void deleteById(Integer id){
        projectUserClassifyService.deleteById(id);
    }

    /**
     * @param technicalExpertise 根据服务的产业或领域查询分组
     * @return 所有该服务的产业或领域的分组
     */
    @GetMapping("/group/{technicalExpertise}")
    public List<ProjectUserClassify> findGroupByTechnicalExpertise(@PathVariable String technicalExpertise) {
        return projectUserClassifyService.findGroupByTechnicalExpertise(technicalExpertise);
    }

    /**
     * @param userId 根据系统表用户id查询
     * @return 返回一个对象
     */
    @GetMapping("/user/{userId}")
    public List<ProjectUserClassify> findByUserId(@PathVariable Integer userId) {
        return projectUserClassifyService.findByUserId(userId);
    }
}
