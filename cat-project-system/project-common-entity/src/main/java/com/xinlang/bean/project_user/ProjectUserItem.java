package com.xinlang.bean.project_user;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 项目用户关系
 * @author 张龙毅 18777811286@163.com
 * 2019-11-09
 */
@Data
@Table(name = "project_user_item")
public class ProjectUserItem implements Serializable {
    private static final long serialVersionUID = 1168471872330445527L;
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    private Integer userId;
    private String userType;//用户类型
    private Integer itemId;//项目id
    private String labelSign;//项目类型标签中的一个
    private Date createTime;
    private Date updateTime;
    @Transient
    private List<ProjectUser> experts;


}
