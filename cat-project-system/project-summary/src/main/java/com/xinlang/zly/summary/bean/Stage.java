package com.xinlang.zly.summary.bean;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.ArrayList;
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
    private String itemName;//项目名称
    private String contractNo;//合同编号
    private String category;//项目类别
    private String undertaker;//承担单位
    private String createUserEmail;//填报人邮箱
    private String createUserPhone;//填报人电话
    private String startAndEndTime;//起止时间
    private Integer createUserId;//填表人id
    private String createUserName;//填表人姓名
    private Date createTime;//填报时间
    private String applicantExtension;//是否申请延期
    private Date extensionTime;//延期批复截止时间
    private String produceLearnResearch;//产学研联合
    private String innovateType;//创新类型
    private String collaborate;//合作形式
    private String skillSourceDept;//主要技术来源单位
    private String progress;//项目进展情况
    private String cause;//项目拖延、停顿、终止、撤销的主要原因
    private String skillTarget;//技术指标
    private String skillTargetSchedule;//技术指标进度
    private String skillTargetDescribe;//技术指标描述
    private String economyTarget;//经济指标
    private String economyTargetSchedule;//经济指标进度
    private String economyTargetDescribe;//经济指标描述
    private String personTarget;//人才队伍建设指标进度
    private String personTargetSchedule;//人才队伍建设指标进度
    private String personTargetDescribe;//人才队伍建设指标描述
    private String otherTarget;//其他指标进度
    private String otherTargetSchedule;//其他指标进度
    private String otherTargetDescribe;//其他指标描述
    private String importanceDescribe;//项目重要进展情况描述
    private String isOneCheck;//项目资金是否单独核算
    private String  oneCheckType;//单独核算形式
    private String allIncome;//收入 
    private String allExpend;//支出
    private String labourExpend;//劳务支出
    private String performanceExpend;//缴费支出
    private String stateSkillDeptAppropriation;//国家科技部门拨款
    private String stateOtherDeptAppropriation;//国家其他部门拨款
    private String districtDeptAppropriation;//区级部门拨款
    private String cityDeptAppropriation;//市级部门拨款
    private String countyDeptAppropriation;//县级部门拨款
    private String selfRaisedFund;//自筹资金
    private String bankLoan;//银行贷款
    private String foreignFund;//国外资金
    private String otherFund;//其他资金
    private String principal;//负责人
    private String principalCheck;//负责人审核意见
    private Integer principalCheckEnable;//负责人审核结果
    private Date principalCheckTime ;//负责人审核时间
    private String projectCompletion;//项目完成情况
    private String deptCheck;//单位审核意见
    private Integer deptCheckEnable;//单位审核结果
    private String deptCheckUser;//经办人
    private Date deptCheckTime;//单位审核时间
    private Integer enable;//状态
    @Transient
    private List<String> causes;//项目拖延、停顿、终止、撤销的主要原因
    @Transient
    private List<StageAffixFile> stageAffixFiles = new ArrayList<>();//附件
    @Transient
    private List<StageFundUse> stageFundUses = new ArrayList<>();//资金使用情况
    @Transient
    private List<StageKpi> stageKpis;//考核指标
    @Transient
    private List<StageTarget> stageTargets = new ArrayList<>();//项目效益及成果基本情况
    @Transient
    private List<StagePaperCatalogue> stagePaperCatalogues = new ArrayList<>();//专著、论文目录
    @Transient
    private List<StagePatentCatalogue> stagePatentCatalogues = new ArrayList<>();//专利目录
    @Transient
    private List<StageScienceAchievementAwardCatalogue> stageScienceAchievementAwardCatalogues = new ArrayList<>();//科技成果获得奖励目录
    @Transient
    private List<StageScienceAchievementRegisterCatalogue> stageScienceAchievementRegisterCatalogues = new ArrayList<>();//科技成果登记目录
    @Transient
    private List<StageTechnologyPactRegisterCatalogue> stageTechnologyPactRegisterCatalogues = new ArrayList<>();//技术合同目录
}
