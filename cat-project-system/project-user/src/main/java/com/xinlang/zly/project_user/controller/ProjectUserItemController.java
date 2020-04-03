package com.xinlang.zly.project_user.controller;

import com.xinlang.bean.project_user.ProjectUser;
import com.xinlang.bean.project_user.ProjectUserDomain;
import com.xinlang.bean.project_user.ProjectUserItem;
import com.xinlang.bean.project_user.ProjectUserType;
import com.xinlang.zly.project_user.service.IProjectUserDomainService;
import com.xinlang.zly.project_user.service.IProjectUserItemService;
import com.xinlang.zly.project_user.service.IProjectUserService;
import com.xinlang.zly_xyx.log.LogAnnotation;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.*;


/**
 * @author 张龙毅 18777811286@163.com
 * 2019-11-09
 */
@RestController
public class ProjectUserItemController {

    @Autowired
    private IProjectUserItemService projectUserItemService;
    @Autowired
    private IProjectUserDomainService projectUserDomainService;
    @Autowired
    private IProjectUserService projectUserService;

    @PostMapping("/item/list")
    @ApiOperation(value = "params:List<ProjectUserItem>")
    @LogAnnotation(module = "添加用户与项目关系")
    public List<ProjectUserItem> saveList(@RequestBody List<ProjectUserItem> projectUserItems) {
        Date date = new Date();
        Map<String, Object> map = new HashMap<>();
        for (ProjectUserItem projectUserItem : projectUserItems) {
            map.put("itemId", projectUserItem.getItemId());
            map.put("userId", projectUserItem.getUserId());
            List<ProjectUserItem> list = projectUserItemService.findListByParams(map, ProjectUserItem.class);
            if (!list.isEmpty()) {
                projectUserItem.setCreateTime(date);
                projectUserItemService.save(projectUserItem);
            }
        }
        return projectUserItems;
    }

    @PostMapping("/item")
    @ApiOperation(value = "全参不包括id")
    @LogAnnotation(module = "添加用户与项目关系")
    public ProjectUserItem save(@RequestBody ProjectUserItem projectUserItem) {
        Date date = new Date();
        projectUserItem.setCreateTime(date);
        return projectUserItem;
    }

    @PostMapping(value = "/item/random/match/expert")
    @ApiOperation(value = "随机匹配专家")
    @LogAnnotation(module = "随机匹配专家")
    //@PreAuthorize("hasAnyAuthority('project:item:match:expert')")
    public ProjectUserItem randomMatchExpert(String labelSign, Integer population, Integer itemId) {

        //找到所有拥有{labelSign}标签的专家
        List<ProjectUserDomain> domains = projectUserDomainService.findByLabelSign(labelSign, ProjectUserType.EXPERT.name());
        if (!"".equals(domains) || null == domains) {
            throw new IllegalArgumentException("没有该专业的专家");
        }
        if (population > domains.size()) {
            throw new IllegalArgumentException("没有足够的专家");
        }
        //取出所有专家id
        Set<Integer> userIds = new HashSet<>();
        domains.forEach(domain -> {
            userIds.add(domain.getUserId());
        });
        //查询出所有该类型专家
        List<ProjectUser> users = projectUserService.findByUserIds(userIds);
        //查询每个专家服务次数及最后一次服务时间，并set到user中暂存
        users.forEach(user -> {
            List<ProjectUserItem> items = projectUserItemService.findByUserIdAndUserType(user.getUserId(), ProjectUserType.EXPERT.name());
            user.setTime(items.size());
            if (items.isEmpty()) {
                user.setEndServerTime(user.getCreateTime());
            } else {
                user.setEndServerTime(items.get(0).getCreateTime());
            }
        });
        //根据服务次数排序
        Collections.sort(users, new Comparator<ProjectUser>() {
            @Override
            public int compare(ProjectUser o1, ProjectUser o2) {
                int i = o1.getTime() - o2.getTime();
                if (i == 0) {
                    i = o1.getEndServerTime().compareTo(o2.getEndServerTime());
                }
                return i;
            }
        });
        //按照数量取出前面部分的。
        List<ProjectUser> user1 = users.subList(0, population);
        ProjectUserItem item = new ProjectUserItem();
        user1.forEach(user -> {
            Date date = new Date();
            item.setCreateTime(date);
            item.setUpdateTime(date);
            item.setLabelSign(labelSign);
            item.setUserId(user.getUserId());
            item.setUserType(ProjectUserType.EXPERT.name());
            item.setItemId(itemId);
            save(item);
        });
        item.setExperts(user1);
        return item;
    }


    @ApiOperation(value = "根据标签获取专家集合,return:Map<String(labelSign),List<ProjectUser>>")
    @LogAnnotation(module = "根据标签获取专家指派专家")
    @GetMapping(value = "/item/assign/expert/{labelSigns}")
    //@PreAuthorize("hasAnyAuthority('project:item:match:expert')")
    public Map<String, List<ProjectUser>> assignExpert(@PathVariable Set<String> labelSigns) {
        Map<String, List<ProjectUser>> result = new HashMap<>();
        for (String labelSign : labelSigns) {
            //找到所有拥有{labelSign}标签的专家
            List<ProjectUserDomain> domains = projectUserDomainService.findByLabelSign(labelSign, ProjectUserType.EXPERT.name());
            //取出所有专家id
            Set<Integer> userIds = new HashSet<>();
            domains.forEach(domain -> {
                userIds.add(domain.getUserId());
            });
            //查询出所有该类型专家
            List<ProjectUser> users = null;
            if (!userIds.isEmpty()) {
                users = projectUserService.findByUserIds(userIds);
            }
            result.put(labelSign, users);
        }
        return result;
    }

    @DeleteMapping("/item/{id}")
    @ApiOperation(value = "删除用户与项目关系")
    @LogAnnotation(module = "删除用户与项目关系")
    // @PreAuthorize("hasAnyAuthority('project:item:delete')")
    public void delete(@PathVariable Integer id) {
        projectUserItemService.delete(id);
    }

    @DeleteMapping(value = "/item", params = {"itemId", "labelSign", "userType"})
    @ApiOperation(value = "随机移除一位专家,仅限匹配时")
    @LogAnnotation(module = "随机移除一位专家,")
    //@PreAuthorize("hasAnyAuthority('project:item:random:delete')")
    public void randomDelete(Integer itemId, String labelSign, String userType) {
        if (userType == null || "".equals(userType)) {
            userType = ProjectUserType.EXPERT.name();
        }
        projectUserItemService.randomDelete(itemId, labelSign, userType);
    }
}
