package com.xinlang.zly.summary.bean;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@Table(name="project_summary_expert_evaluate_affiliate")
public class ExpertEvaluateAffiliate implements Serializable {
    private static final long serialVersionUID = 2593730946500997232L;
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    private Integer userId;
    private Integer expertEvaluateId;
    private Integer itemId;
    private Integer targetId;
    private String content;
    private String checked;
    private String userName;
    private Date createTime;
    private Date updateTime;
}
