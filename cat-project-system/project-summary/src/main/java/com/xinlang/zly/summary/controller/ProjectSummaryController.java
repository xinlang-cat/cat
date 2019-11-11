package com.xinlang.zly.summary.controller;

import com.xinlang.zly.summary.bean.ProjectSummary;
import com.xinlang.zly.summary.service.IProjectSummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019-11-11
 */
@RestController
public class ProjectSummaryController {
    @Autowired
    private IProjectSummaryService projectSummaryService;

    @PostMapping("/summary")
    public ProjectSummary save(@RequestBody ProjectSummary projectSummary){
        projectSummaryService.save(projectSummary);
        return projectSummary;
    }

    @PutMapping("/summary")
    public ProjectSummary update(@RequestBody ProjectSummary projectSummary){
        projectSummaryService.update(projectSummary);
        return projectSummary;
    }

    @GetMapping(value = "/summary",params = {"userId","itemId"})
    public ProjectSummary findByUserIdAndItemId(Integer userId,Integer itemId){
        return projectSummaryService.findByUserIdAndItemId(userId,itemId);
    }

    @DeleteMapping("/summary/{id}")
    public void delete(@PathVariable Integer id){
        projectSummaryService.delete(id);
    }
}
