package com.xinlang.zly.summary.bean;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
@Data
@Table(name = "project_summary_stage_paper_catalogue")
public class StagePaperCatalogue implements Serializable {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    private Integer stageId;//报告id
    private String paperTitle;//专利、论文标题
    private String paperPersonCharge;//完成人
    private String paperLeaderRank;//项目负责人排位
    private Date paperPostedTime;//发表时间
    private String paperCategory;//论文收录类别
    private Integer paperCitationsSum;//论文他人引用次数
    private String paperRemark;//有关说明
}
