package com.xinlang.zly.project_user.controller;

import com.xinlang.bean.company.CompanyUser;
import com.xinlang.bean.project_user.ProjectUser;
import com.xinlang.bean.project_user.ProjectUserDomain;
import com.xinlang.bean.project_user.ProjectUserSkill;
import com.xinlang.bean.project_user.ProjectUserType;
import com.xinlang.zly.project_user.fegin.ConsumeCatUser;
import com.xinlang.zly.project_user.fegin.ConsumeCompanyUser;
import com.xinlang.zly.project_user.mapper.ProjectUserDomainMapper;
import com.xinlang.zly.project_user.mapper.ProjectUserSkillMapper;
import com.xinlang.zly.project_user.service.IProjectUserDomainService;
import com.xinlang.zly.project_user.service.IProjectUserSkillService;
import com.xinlang.zly_xyx.common.Page;
import com.xinlang.zly_xyx.log.LogAnnotation;
import com.xinlang.zly_xyx.user.AppUser;
import com.xinlang.zly_xyx.user.SysRole;
import com.xinlang.zly_xyx.user.constants.UserType;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.xinlang.zly.project_user.service.IProjectUserService;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019-11-05
 */
@RestController
public class ProjectUserController {

    @Autowired
    private IProjectUserService projectUserService;
    @Autowired
    private IProjectUserSkillService projectUserSkillService;
    @Autowired
    private IProjectUserDomainService projectUserDomainService;
    @Autowired
    private ConsumeCatUser consumeCatUser;
    @Autowired
    private ConsumeCompanyUser consumeCompanyUser;

    @ApiOperation(value = "添加用户信息,全参不包含id")
    @LogAnnotation(module = "添加用户信息")
    @PostMapping("/user")
    public ProjectUser save(@RequestBody ProjectUser projectUser){
        projectUserService.save(projectUser);
        Long userId = projectUser.getUserId().longValue();
        if(ProjectUserType.EXPERT.name().equals(projectUser.getUserType())){
            consumeCatUser.setDefaultRoleToUser(userId,consumeCatUser.findByCode(projectUser.getUserType()).getId());
        }
        if(ProjectUserType.PARTY_A.name().equals(projectUser.getUserType())){
            consumeCatUser.setDefaultRoleToUser(userId,consumeCatUser.findByCode(projectUser.getUserType()).getId());
        }
        if(ProjectUserType.PARTY_B_MEMBER.name().equals(projectUser.getUserType())){
            consumeCatUser.setDefaultRoleToUser(userId,consumeCatUser.findByCode("PARTY_B:MEMBER").getId());
        }
        if(ProjectUserType.PARTY_B_PRINCIPAL.name().equals(projectUser.getUserType())){
            consumeCatUser.setDefaultRoleToUser(userId,consumeCatUser.findByCode("PARTY_B:PRINCIPAL").getId());
        }
        if(ProjectUserType.PARTY_C.name().equals(projectUser.getUserType())){
            consumeCatUser.setDefaultRoleToUser(userId,consumeCatUser.findByCode(projectUser.getUserType()).getId());
        }
        if(ProjectUserType.PARTY_D.name().equals(projectUser.getUserType())){
            consumeCatUser.setDefaultRoleToUser(userId,consumeCatUser.findByCode(projectUser.getUserType()).getId());
        }
        return projectUser;
    }

    @ApiOperation(value = "添加用户信息,全参不包含id")
    @LogAnnotation(module = "添加用户信息")
    @PostMapping("/user-anon/save")
    public void saveAnon(@RequestBody Map<String,Object> params){
        Date date = new Date();
        AppUser appUser = new AppUser();
        appUser.setPassword(params.get("password").toString());
        appUser.setUsername(params.get("phone").toString());
        appUser.setNickname(params.get("username").toString());
        appUser.setSex(Integer.valueOf(params.get("sex").toString()));
        appUser.setPhone(params.get("phone").toString());
        appUser.setCreateTime(date);
        appUser.setType(UserType.BACKEND.name());
        appUser.setEnabled(true);
        appUser = consumeCatUser.register(appUser);
        CompanyUser companyUser = new CompanyUser();
        companyUser.setUserId(appUser.getId().intValue());
        companyUser.setDeptCode(params.get("deptCode").toString());
        companyUser.setName(appUser.getNickname());
        consumeCompanyUser.save(companyUser);
        ProjectUser projectUser = new ProjectUser();
        projectUser.setName(appUser.getNickname());
        projectUser.setPhone(appUser.getPhone());
        projectUser.setDeptCode(params.get("deptCode").toString());
        projectUser.setDeptName(params.get("deptName").toString());
        projectUser.setUserId(appUser.getId().intValue());
        projectUser.setUserType(params.get("userType").toString());
        projectUser.setSex(params.get("sex").toString());
        projectUser.setCreateTime(date);
        projectUserService.save(projectUser);
        Long userId = projectUser.getUserId().longValue();
        if(ProjectUserType.EXPERT.name().equals(projectUser.getUserType())){
            consumeCatUser.setDefaultRoleToUser(userId,consumeCatUser.findByCode(projectUser.getUserType()).getId());
        }
        if(ProjectUserType.PARTY_A.name().equals(projectUser.getUserType())){
            consumeCatUser.setDefaultRoleToUser(userId,consumeCatUser.findByCode(projectUser.getUserType()).getId());
        }
        if(ProjectUserType.PARTY_B_MEMBER.name().equals(projectUser.getUserType())){
            consumeCatUser.setDefaultRoleToUser(userId,consumeCatUser.findByCode("PARTY_B:MEMBER").getId());
        }
        if(ProjectUserType.PARTY_B_PRINCIPAL.name().equals(projectUser.getUserType())){
            consumeCatUser.setDefaultRoleToUser(userId,consumeCatUser.findByCode("PARTY_B:PRINCIPAL").getId());
        }
        if(ProjectUserType.PARTY_C.name().equals(projectUser.getUserType())){
            consumeCatUser.setDefaultRoleToUser(userId,consumeCatUser.findByCode(projectUser.getUserType()).getId());
        }
        if(ProjectUserType.PARTY_D.name().equals(projectUser.getUserType())){
            consumeCatUser.setDefaultRoleToUser(userId,consumeCatUser.findByCode(projectUser.getUserType()).getId());
        }
    }

    @ApiOperation(value = "修改用户信息，id必填")
    @LogAnnotation(module = "修改用户信息")
    @PutMapping("/user")
    public ProjectUser update(@RequestBody ProjectUser projectUser){
        projectUserService.update(projectUser);
        Long userId = projectUser.getUserId().longValue();
        if(ProjectUserType.EXPERT.name().equals(projectUser.getUserType())){
            consumeCatUser.setDefaultRoleToUser(userId,consumeCatUser.findByCode(projectUser.getUserType()).getId());
        }
        if(ProjectUserType.PARTY_A.name().equals(projectUser.getUserType())){
            consumeCatUser.setDefaultRoleToUser(userId,consumeCatUser.findByCode(projectUser.getUserType()).getId());
        }
        if(ProjectUserType.PARTY_B_MEMBER.name().equals(projectUser.getUserType())){
            consumeCatUser.setDefaultRoleToUser(userId,consumeCatUser.findByCode("PARTY_B:MEMBER").getId());
        }
        if(ProjectUserType.PARTY_B_PRINCIPAL.name().equals(projectUser.getUserType())){
            consumeCatUser.setDefaultRoleToUser(userId,consumeCatUser.findByCode("PARTY_B:PRINCIPAL").getId());
        }
        if(ProjectUserType.PARTY_C.name().equals(projectUser.getUserType())){
            consumeCatUser.setDefaultRoleToUser(userId,consumeCatUser.findByCode(projectUser.getUserType()).getId());
        }
        if(ProjectUserType.PARTY_D.name().equals(projectUser.getUserType())){
            consumeCatUser.setDefaultRoleToUser(userId,consumeCatUser.findByCode(projectUser.getUserType()).getId());
        }
        return projectUser;
    }

    @ApiOperation(value = "查询所有用户信息")
    @LogAnnotation(module = "查询所有用户信息")
    @GetMapping("/user/all")
    public List<ProjectUser> findAll(){
        List<ProjectUser> list =  projectUserService.findAll();
        setDomainAndSkill(list);
        return list;
    }

    @ApiOperation(value = "根据用户类型查询用户信息")
    @LogAnnotation(module = "根据用户类型查询用户信息")
    @GetMapping("/user/type/{userType}")
    public List<ProjectUser> findByUserType(@PathVariable String userType){
        List<ProjectUser> list =  projectUserService.findByUserType(userType);
        setDomainAndSkill(list);
        return list;
    }

    @GetMapping("/users")
    @LogAnnotation(module = "根据实体中的属性查询用户")
    @ApiOperation(value = "根据实体中的属性查询")
    public Page<ProjectUser> findByParams(@RequestParam Map<String,Object> params){
        Page<ProjectUser> page = projectUserService.findByParams(params);
        setDomainAndSkill(page.getData());
        return page;
    }

    @ApiOperation(value =  "根据系统用户表id查询用户信息")
    @LogAnnotation(module = "根据系统用户表id查询用户信息")
    @GetMapping("/user-anon/{userId}")
    public List<ProjectUser> findByUserId(@PathVariable Integer userId){
        List<ProjectUser> list =   projectUserService.findByUserId(userId);
        setDomainAndSkill(list);
        return list;
    }

    @ApiOperation(value =  "根据系统用户表id查询用户信息")
    @LogAnnotation(module = "根据系统用户表id查询用户信息")
    @GetMapping("/user/{ids}")
    public List<ProjectUser> findByUserIds(@PathVariable Set<Integer> ids){
        List<ProjectUser> list =   projectUserService.findByUserIds(ids);
        setDomainAndSkill(list);
        return list;
    }

    @ApiOperation(value = "根据id删除用户信息")
    @LogAnnotation(module = "根据id删除用户信息")
    @PreAuthorize("hasAnyAuthority('project:user:delete')")
    @DeleteMapping("/user/{id}")
    public void deleteByUserId(@PathVariable Integer id){
        projectUserService.delete(id);
    }


    private void setDomainAndSkill(List<ProjectUser> list){
        list.forEach(pu->{
            List<ProjectUserDomain> domains = projectUserDomainService.findByUserId(pu.getUserId());
            List<ProjectUserSkill> skills = projectUserSkillService.findByUserId(pu.getUserId());
            pu.setDomains(domains);
            pu.setSkills(skills);
        });
    }
}
