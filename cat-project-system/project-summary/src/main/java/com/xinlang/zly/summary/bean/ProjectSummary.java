package com.xinlang.zly.summary.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019-11-11
 */
@Data
@Table(name = "project_summary")
public class ProjectSummary implements Serializable {
    private static final long serialVersionUID = 1340653261208252531L;
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    private Integer userId;//系统用户表id
    private Integer userType;//用户类型
    private String name;//用户姓名
    private Integer itemId;//项目表id
    private String content;//内容
    private Date createTime;
    private Date updateTime;
}
