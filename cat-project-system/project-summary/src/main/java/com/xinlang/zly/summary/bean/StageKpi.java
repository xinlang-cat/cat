package com.xinlang.zly.summary.bean;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Table(name = "project_summary_stage_kpi")
public class StageKpi implements Serializable {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    private Integer stageId;
    private Integer kpiType;//指标类型
    private String kpiContent;//指标内容
    private String kpiStatus;//完成情况
    private String kpiStatusRemark;//完成情况描述
}
