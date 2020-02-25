package com.xinlang.zly.summary.bean;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;
import tk.mybatis.mapper.genid.GenId;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@Table(name = "project_summary_stage_patent_catalogue")
public class StagePatentCatalogue implements Serializable {

    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    private Integer stageId;//报告id
    private String patentNumber;//专利编号
    private String patentPersonCharge;//完成人
    private String patentLeaderRank;//项目负责人排位
    private String patentType;//专利类型
    private Date patentApplicationTime;//申请专利时间
    private Date patentAuthorizeTime;//专利授权时间
    private String patentRemark;//有关说明
}
