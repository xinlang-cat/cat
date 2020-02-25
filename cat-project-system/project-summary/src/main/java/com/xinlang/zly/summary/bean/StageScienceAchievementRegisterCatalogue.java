package com.xinlang.zly.summary.bean;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@Table(name = "project_summary_stage_science_achievement_register_catalogue")
public class StageScienceAchievementRegisterCatalogue implements Serializable {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    private Integer stageId;//报告id
    private String scienceRegisterNumber;//登记号
    private String scienceRegisterAchievementName;//成果名称
    private String scienceRegisterAccomplishDept;//第一完成单位
    private String scienceRegisterPersonCharge;//第一完成人
}
