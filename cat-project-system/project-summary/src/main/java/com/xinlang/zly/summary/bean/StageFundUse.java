package com.xinlang.zly.summary.bean;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Table(name = "project_summary_stage_fund_use")
public class StageFundUse implements Serializable {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    private Integer stageId;
    private String fundUseSubject;//科目
    private float fundUseExpenditure;//支出金额
    private float fundUseOriginalBudget;//原始合同预算
    private float fundUseExpenditureOut;//已经支出金额
    private String fundUseRemark;//开支内容
}
