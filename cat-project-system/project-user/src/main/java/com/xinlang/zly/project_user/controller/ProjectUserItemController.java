package com.xinlang.zly.project_user.controller;

import com.xinlang.bean.project_user.ProjectUser;
import com.xinlang.bean.project_user.ProjectUserDomain;
import com.xinlang.bean.project_user.ProjectUserItem;
import com.xinlang.bean.project_user.ProjectUserType;
import com.xinlang.zly.project_user.service.IProjectUserDomainService;
import com.xinlang.zly.project_user.service.IProjectUserItemService;
import com.xinlang.zly.project_user.service.IProjectUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

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

    /**
     * 匹配专家
     * @param labelSign
     * @param population
     * @return
     */
    @PostMapping(value = "/item/match/expert",params = {"labelSign","population","itemId"})
    public ProjectUserItem matchExpert(String labelSign,Integer population,Integer itemId){
        //找到所有拥有{labelSign}标签的专家
       List<ProjectUserDomain> domains = projectUserDomainService.findByLabelSign(labelSign, ProjectUserType.EXPERT.name());
       if (population>domains.size()){
           throw new IllegalArgumentException("没有足够的专家");
       }
       //取出所有专家id
       Set<Integer> userIds = new HashSet<>();
       domains.forEach(domain->{
          userIds.add(domain.getUserId());
        });
       //查询出所有该类型专家
        List<ProjectUser>  users = projectUserService.findByUserIds(userIds);
        //查询每个专家服务次数及最后一次服务时间，并set到user中暂存
        users.forEach(user->{
            List<ProjectUserItem> items = projectUserItemService.findByUserIdAndUserType(user.getUserId(),ProjectUserType.EXPERT.name());
            user.setTime(items.size());
            if(items.isEmpty()){
                user.setEndServerTime(user.getCreateTime());
            }else {
                user.setEndServerTime(items.get(0).getCreateTime());
            }
        });
       //根据服务次数排序
        Collections.sort(users, new Comparator<ProjectUser>() {
            @Override
            public int compare(ProjectUser o1, ProjectUser o2) {
                int i = o1.getTime()-o2.getTime();
                if(i==0){
                    i = o1.getEndServerTime().compareTo(o2.getEndServerTime());
                }
                return i;
            }
        });
        //按照数量取出前面部分的。
        List<ProjectUser> user1 = users.subList(0,population);
        ProjectUserItem item = new ProjectUserItem();
        user1.forEach(user->{
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

    @DeleteMapping("/item/{id}")
    public void delete(@PathVariable Integer id){
        projectUserItemService.delete(id);
    }

    @DeleteMapping(value = "/item",params = {"itemId","labelSign","userType"})
    public void randomDelete(Integer itemId,String labelSign,String userType){
        if(userType == null || "".equals(userType)){
            userType = ProjectUserType.EXPERT.name();
        }
        projectUserItemService.randomDelete(itemId,labelSign,userType);
    }
}
