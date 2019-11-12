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
@ApiModel(value = "项目总结对象",description = "项目总结ProjectSummary对象")
public class ProjectSummary implements Serializable {
    private static final long serialVersionUID = 1340653261208252531L;
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    @ApiModelProperty(value="系统用户表id",name="userId",required=true)
    private Integer userId;
    @ApiModelProperty(value="用户类型",name="userType",required=true)
    private Integer userType;
    @ApiModelProperty(value="用户姓名",name="name",required=true)
    private String name;
    @ApiModelProperty(value="项目表id",name="itemId",required=true)
    private Integer itemId;
    @ApiModelProperty(value="内容",name="content",required=true)
    private String content;
    private Date createTime;
    private Date updateTime;
}
