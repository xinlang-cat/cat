package com.xinlang.zly.project_user.controller;

import com.xinlang.bean.project_user.ProjectUserItem;
import com.xinlang.zly.project_user.service.IProjectUserItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019-11-09
 */
@RestController
public class ProjectUserItemController {

    @Autowired
    private IProjectUserItemService projectUserItemService;

    /**
     * 全参不包括id
     * @param projectUserItem
     * @return ProjectUserItem
     */
    @PostMapping("/item")
    public ProjectUserItem save(ProjectUserItem projectUserItem) {
        projectUserItemService.save(projectUserItem);
        return projectUserItem;
    }


    

}
