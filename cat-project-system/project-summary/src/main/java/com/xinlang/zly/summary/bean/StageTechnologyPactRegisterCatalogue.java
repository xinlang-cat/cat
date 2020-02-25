package com.xinlang.zly.summary.bean;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@Table(name = "project_summary_stage_technology_pact_register_catalogue")
public class StageTechnologyPactRegisterCatalogue implements Serializable {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    private Integer stageId;//报告
    private String technologyPactNumber;//技术合同编号
    private String technologyPactName;//技术合同名称
    private String technologySellerDept;//卖方单位
    private String technologyPactVolumeTransaction;//合同成交金额
    private String technologyVolumeTransaction;//技术交易金额
    private String technologyPactType;//技术交易类型
}
