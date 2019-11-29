package com.xinlang.bean.project_user;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 技术专长及能提供的服务
 * @author 张龙毅 18777811286@163.com
 * 2019-11-09
 */
@Data
@Table(name = "project_user_skill")
public class ProjectUserSkill implements Serializable {

    private static final long serialVersionUID = -1882573547854616739L;

    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    private Integer userId;
    private String userType;
    private String labelSign;//技术专长标签代码
    private Date createTime;
    private Date updateTime;
}
