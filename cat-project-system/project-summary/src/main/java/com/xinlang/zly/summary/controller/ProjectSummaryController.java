package com.xinlang.zly.summary.controller;

import com.xinlang.zly.summary.bean.ProjectSummary;
import com.xinlang.zly.summary.service.IProjectSummaryService;
import com.xinlang.zly_xyx.common.Page;
import com.xinlang.zly_xyx.log.LogAnnotation;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019-11-11
 */
@Api("项目总结接口")
@RestController
public class ProjectSummaryController {
    @Autowired
    private IProjectSummaryService projectSummaryService;

    @PostMapping("/summary")
    @LogAnnotation(module = "添加项目总结")
    @ApiOperation(value = "添加项目总结，全参，不包含id")
    public ProjectSummary save(@RequestBody ProjectSummary projectSummary){
        projectSummaryService.save(projectSummary);
        return projectSummary;
    }

    @PutMapping("/summary")
    @LogAnnotation(module = "修改项目总结")
    @ApiOperation(value="修改项目总结")
    public ProjectSummary update(@RequestBody ProjectSummary projectSummary){
        projectSummaryService.update(projectSummary);
        return projectSummary;
    }

    @ApiOperation(value="根据用户id和项目id查询list")
    @GetMapping(value = "/summary",params = {"userId","itemId"})
    @LogAnnotation(module = "根据用户id和项目id查询list")
    public ProjectSummary findByUserIdAndItemId(Integer userId, Integer itemId){
        return projectSummaryService.findByUserIdAndItemId(userId,itemId);
    }

    /**
     * @param userId
     * @return
     */
    @ApiOperation(value="根据用户id查询list")
    @GetMapping( "/summary/user/{userId}")
    @LogAnnotation(module = "根据用户id查询list")
    public List<ProjectSummary> findByUserId(@PathVariable Integer userId){
        return projectSummaryService.findByUserId(userId);
    }

    @ApiOperation(value="根据项目id和用户类型查询list",notes="userType可以为空")
    @GetMapping( value = "/summary/item/{itemId}",params = {"userType"})
    @LogAnnotation(module = "根据项目id和用户类型查询listt")
    public List<ProjectSummary> findByItemIdAndUserType(@PathVariable Integer itemId, String userType){
        return projectSummaryService.findByItemIdAndUserType(itemId,userType);
    }

    @GetMapping("/summarys")
    @LogAnnotation(module = "根据实体中的属性查询项目总结")
    @ApiOperation(value = "根据实体中的属性查询")
    public Page<ProjectSummary> findByParams(@RequestParam Map<String,Object> params){
        return projectSummaryService.findByParams(params);
    }

    @ApiOperation(value="根据id删除一条记录")
    @DeleteMapping("/summary/{id}")
    @LogAnnotation(module = "根据id删除一条记录")
    public void delete(@PathVariable Integer id){
        projectSummaryService.delete(id);
    }
}
