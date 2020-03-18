package com.xinlang.zly.summary.bean;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019-12-17
 * 项目验收申请表
 */
@Data
@Table(name = "project_check_table")
public class CheckTable implements Serializable {

    private static final long serialVersionUID = -5892352911549042533L;
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    private Integer createUserId;//创建人id
    private Integer itemId;//项目id
    private Date applicationDate;//申请日期
    private Date suggestDate;//申请验收单位建议的验收日期
    private String cean;//项目合同（或课件任务书）约定的目标任务与技术经济指标完成情况
    private String katalog;//提交的验收文件和资料目录
    private String applicationDeptOpinion;// 申请验收单位意见
    private String manageDeptOpinion;//受委托管理单位或项目牵头承担单位意见
    private String burgLeaderOpinion;//自治区科技同项目
    private String burgFinanceOpinion;//自治区科技厅条件财务处意见

    private Integer THE_IMPORTED_TECHNOLOGY;
    private Integer INTEGRATED_APPLICATION_TECHNOLOGY;
    private Integer NEW_INDUSTRIAL_PRODUCT;
    private Integer NEW_AGRICULTURAL_VARIETY;
    private Integer THE_NEW_TECHNOLOGY;
    private Integer THE_NEW_MATERIAL;
    private Integer THE_NEW_DEVICE;
    private Integer REGISTER_COMPUTER_SOFTWARE;
    private Integer RESEARCH_AND_DEVELOPMENT_PLATFORM;
    private Integer SCIENCE_INFORMATION_PLATFORM;
    private Integer PILOT_STUDIES;
    private Integer PILOT_LINE;
    private Integer THE_PRODUCTION_LINE;
    private Integer EXPERIMENTAL_BASE;
    private Integer APPLICATION_FOR_PATENT;
    private Integer APPLICATION_FOR_INVENTION_PATENT;
    private Integer APPLICATION_FOR_UTILITY_PATEN;
    private Integer AUTHORIZED_PATENT;
    private Integer AUTHORIZED_INVENTION_PATENT;
    private Integer AUTHORIZED_UTILITY_PATENT;
    private Integer COUNT_STANDARD;
    private Integer THE_NATIONAL_STANDARD;
    private Integer THE_INTERNATIONAL_STANDARD;
    private Integer THE_INDUSTRY_STANDARD;
    private Integer LOCAL_STANDARD;
    private Integer THE_ENTERPRISE_STANDARD;
    private Integer COUNT_THESIS;
    private Integer DOMESTIC_PAPERS;
    private Integer FOREIGN_PAPERS;
    private Integer MAKE_POSTDOCTOR;
    private Integer MAKE_DOCTOR;
    private Integer MAKE_POSTGRADUATE;
    private Integer HOLD_TRAINING_COURSES;
    private Integer NUMBER_OF_PARTICIPANTS;
    private Integer CONVERT_THE_NUMBER_OF_APPLICATIONS;
    private Integer RESULTS_OF_INDUSTRIALIZATION;
    private Integer ACHIEVEMENT_TRANSFER_CONTRACT;
    private Integer TOTAL_AMOUNT_OF_ACHIEVEMENT_TRANSFER_CONTRACT;
    private Integer ALL_PRODUCTION_A;
    private Integer id;
    private Integer id;
    private Integer id;
    private Integer id;
    private Integer id;
    private Integer id;
    private Integer id;
    private Integer id;
    private Integer id;
    private Integer id;
    private Integer id;
    private Integer id;
    private Integer id;
    private Integer id;
    private Integer id;
    private Integer id;
    private Integer id;
    private Integer id;
    private Integer id;
    private Integer id;
    private Integer id;
    private Integer id;
    private Integer id;
    private Integer id;
    private Integer id;
    private Integer id;
    private Integer id;
    private Integer id;
    private Integer id;
    private Integer id;
    private Integer id;
    private Integer id;
    private Integer id;
    private Integer id;
    private Integer id;
    private Integer id;
    private Integer id;
    private Integer id;
    private Integer id;
    private Integer id;
    private Integer id;
    private Integer id;
    private Integer id;
    private Integer id;
    private Integer id;
    private Integer id;
    private Integer id;
    private Integer id;
    private Integer id;
    private Integer id;
    private Integer id;
    private Integer id;
    private Integer id;
    private Integer id;
    private Integer id;
    private Integer id;
    private Integer id;
    private Integer id;
    private Integer id;
    private Integer id;
    private Integer id;
    private Integer id;
    private Integer id;
    private Integer id;
    private Integer id;
    private Integer id;
    private Integer id;
    private Integer id;
    private Integer id;

}
