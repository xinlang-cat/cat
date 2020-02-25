package com.xinlang.zly.summary.bean;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@Table(name = "project_summary_stage_science_achievement_award_catalogue")
public class StageScienceAchievementAwardCatalogue implements Serializable {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    private Integer stageId;//报告id
    private String scienceAwardAchievementName;//成果名称
    private String scienceAwardPersonCharge;//完成人
    private String scienceAwardLeaderRank;//项目负责人排位
    private Date scienceAwardGetTime;//获奖时间
    private String scienceAwardType;//奖励类别
    private String scienceAwardRemark;//有关说明
}
