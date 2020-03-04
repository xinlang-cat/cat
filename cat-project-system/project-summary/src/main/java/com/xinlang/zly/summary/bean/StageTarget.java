package com.xinlang.zly.summary.bean;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "project_summary_stage_target")
public class StageTarget {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    private Integer stageId;
    private String targetSign;
    private String targetName;
    private Integer targetSum;
}
