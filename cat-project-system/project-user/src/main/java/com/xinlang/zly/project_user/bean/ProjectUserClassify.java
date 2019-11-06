package com.xinlang.zly.project_user.bean;

import lombok.Data;

import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.List;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019-11-05
 */
@Data
@Table(name = "project_user_classify")
public class ProjectUserClassify implements Serializable {

    private static final long serialVersionUID = 7741336718458013783L;

    private Integer id;
    private String classifyName;//组名
    private String technicalExpertise;//服务的产业或领域
    private Integer userId;//系统用户表id
    private String idCard;//身份证
    private Integer pid;//组id
    @Transient
    private ProjectUser projectUser;
    @Transient
    private List<ProjectUserClassify> child;

}
