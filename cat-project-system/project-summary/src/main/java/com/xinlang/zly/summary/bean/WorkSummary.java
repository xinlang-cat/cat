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
 * 工作总结
 */
@Data
@Table(name = "project_summary_work")
public class WorkSummary implements Serializable {
    private static final long serialVersionUID = 1340653261208252531L;
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    private Integer itemId;//项目id
    private Integer createUserId;//创建人id
    private String createUserName;//创建人姓名
    private String deptInfo;//企业基本情况
    private String projectContent;//项目背景
    private String fullyFunded;//资金到位情况
    private String skillIndicator;//技术指标完成情况
    private String economyIndicator;//经济指标完成情况
    private String socialBenefit;//项目的社会效益
    private String combinedInfluence;//科技经费支持对企业和项目的影响
    private Date createTime;
    private Date updateTime;
}
