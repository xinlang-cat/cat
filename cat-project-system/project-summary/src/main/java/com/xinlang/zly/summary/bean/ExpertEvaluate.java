package com.xinlang.zly.summary.bean;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 专家评估报告
 */
@Data
@Table(name = "project_summary_expert_evaluate")
public class ExpertEvaluate implements Serializable {

    private static final long serialVersionUID = 353844799983091682L;
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;//id
    private Integer itemId;//项目id
    private String itemName;//项目名称
    private Integer userId;//专家id
    private String userName;//专家姓名
    private Integer isPass;//是否通过1通过，0保留意见，-1不通过
    private String evaluateContent;//评价内容
    private Date createTime;
    private Date updateTime;
    @Transient
    private List<ExpertEvaluateAffiliate> list;
}
