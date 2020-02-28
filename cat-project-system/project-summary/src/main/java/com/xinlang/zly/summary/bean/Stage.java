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
 * @author 张龙毅 18777811286@163.com
 * 2019-01-31
 */
@Data
@Table(name = "project_summary_stage")
public class Stage implements Serializable {

    private static final long serialVersionUID = -4349370566893021101L;

    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;//id
    private Integer itemId;//项目id
    private Integer createUserId;//填表人id
    private String createUserName;//填表人姓名
    private Date createTime;//填报时间
    private Integer applicantExtension;//是否申请延期
    private Date extensionTime;//延期批复截止时间
    private Integer produceLearnResearch;//产学研联合
    private Integer innovateType;//创新类型
    private Integer collaborate;//合作形式
    private Integer skillSourceDept;//主要技术来源单位
    private Integer progress;//项目进展情况
    private String cause;//项目拖延、停顿、终止、撤销的主要原因
    private String skillTargetSchedule;//技术指标进度
    private String skillTargetDescribe;//技术指标描述
    private String economyTargetSchedule;//经济指标进度
    private String economyTargetDescribe;//经济指标描述
    private String personTargetSchedule;//人才队伍建设指标进度
    private String personTargetDescribe;//人才队伍建设指标描述
    private String otherTargetSchedule;//其他指标进度
    private String otherTargetDescribe;//其他指标描述
    private String importanceDescribe;//项目重要进展情况描述
    private Integer isOneCheck;//项目资金是否单独核算
    private Integer  oneCheckType;//单独核算形式
    private double allIncome;//收入
    private double allExpend;//支出
    private double labourExpend;//劳务支出
    private double performanceExpend;//缴费支出
    private String principalCheck;//负责人审核意见
    private Date principalCheckTime ;//负责人审核时间
    private Integer projectCompletion;//项目完成情况
    private String deptCheck;//单位审核意见
    private String deptCheckUser;//经办人
    private Date deptCheckTime;//单位审核时间
    private boolean enable;//状态
    @Transient
    private List<StageAffixFile> stageAffixFiles;//附件
    @Transient
    private List<StageFundUse> stageFundUses;//资金使用情况
    @Transient
    private List<StageKpi> stageKpis;//考核指标
    @Transient
    private List<StagePaperCatalogue> stagePaperCatalogues;//专著、论文目录
    @Transient
    private List<StagePatentCatalogue> stagePatentCatalogues;//专利目录
    @Transient
    private List<StageScienceAchievementAwardCatalogue> stageScienceAchievementAwardCatalogues;//科技成果获得奖励目录
    @Transient
    private List<StageScienceAchievementRegisterCatalogue> stageScienceAchievementRegisterCatalogues;//科技成果登记目录
    @Transient
    private List<StageTechnologyPactRegisterCatalogue> stageTechnologyPactRegisterCatalogues;//技术合同目录
}
