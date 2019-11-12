package com.xinlang.zly.summary.controller;

import com.xinlang.zly.summary.bean.ProjectSummary;
import com.xinlang.zly.summary.service.IProjectSummaryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ProjectSummary save(@RequestBody ProjectSummary projectSummary){
        projectSummaryService.save(projectSummary);
        return projectSummary;
    }

    @PutMapping("/summary")
    public ProjectSummary update(@RequestBody ProjectSummary projectSummary){
        projectSummaryService.update(projectSummary);
        return projectSummary;
    }

    @ApiOperation(value="根据用户id和项目id查询list")
    @GetMapping(value = "/summary",params = {"userId","itemId"})
    public ProjectSummary findByUserIdAndItemId(@ApiParam(name ="userId",value = "系统用户表id",required = true) Integer userId,
                                                @ApiParam(name="itemId",value = "项目id",required = true) Integer itemId){
        return projectSummaryService.findByUserIdAndItemId(userId,itemId);
    }

    /**
     * @param userId
     * @return
     */
    @ApiOperation(value="根据用户id查询list")
    @GetMapping( "/summary/user/{userId}")
    public List<ProjectSummary> findByUserId(@PathVariable @ApiParam(name = "userId",value = "系统用户表id",required = true) Integer userId){
        return projectSummaryService.findByUserId(userId);
    }

    /**
     * @param itemId
     * @param userType
     * @return
     */
    @ApiOperation(value="根据项目id和用户类型查询list",notes="userType可以为空")
    @GetMapping( value = "/summary/item/{itemId}",params = {"userType"})
    public List<ProjectSummary> findByItemIdAndUserType(@PathVariable @ApiParam(name = "itemId",value = "项目id",required = true)Integer itemId,@ApiParam(name = "userType",value = "用户类型") String userType){
        return projectSummaryService.findByItemIdAndUserType(itemId,userType);
    }

    /**
     * 删除
     * @param id
     */
    @ApiOperation(value="根据id删除一条记录")
    @DeleteMapping("/summary/{id}")
    public void delete(@PathVariable @ApiParam(name = "id",value = "项目总结的id",required = true)Integer id){
        projectSummaryService.delete(id);
    }
}
