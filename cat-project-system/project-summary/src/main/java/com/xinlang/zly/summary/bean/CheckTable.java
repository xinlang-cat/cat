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
    private String burgLeaderResult;//验收方核验结果
    private String manageDeptResult;//受委托管理单位或项目牵头承担单位最终核验结果
    private String acceptance_company;//验收单位
    private Integer status;
    /**
     * 申请状态
     * 0-待提交
     * 1-待监理审核
     * 2-待验收方审核
     * 3-专家审核
     * 4-验收方终审
     * 5-监理终审
     */
    private Integer the_company_number;
    private Integer the_imported_technology;
    private Integer integrated_application_technology;
    private Integer new_industrial_product;
    private Integer new_agricultural_variety;
    private Integer the_new_technology;
    private Integer the_new_material;
    private Integer the_new_device;
    private Integer register_computer_software;
    private Integer research_and_development_platform;
    private Integer science_information_platform;
    private Integer pilot_studies;
    private Integer pilot_line;
    private Integer the_production_line;
    private Integer experimental_base;
    private Integer application_for_patent;
    private Integer application_for_invention_patent;
    private Integer application_for_utility_paten;
    private Integer authorized_patent;
    private Integer authorized_invention_patent;
    private Integer authorized_utility_patent;
    private Integer count_standard;
    private Integer the_national_standard;
    private Integer the_international_standard;
    private Integer the_industry_standard;
    private Integer local_standard;
    private Integer the_enterprise_standard;
    private Integer count_thesis;
    private Integer domestic_papers;
    private Integer foreign_papers;
    private Integer make_postdoctor;
    private Integer make_doctor;
    private Integer make_postgraduate;
    private Integer hold_training_courses;
    private Integer number_of_participants;
    private Integer convert_the_number_of_applications;
    private Integer results_of_industrialization;
    private Integer achievement_transfer_contract;
    private Float total_amount_of_achievement_transfer_contract;
    private Float all_production_a;
    private Float the_new_production_a;
    private Float the_new_tax_a;
    private Float net_amount_a;
    private Float all_production_b;
    private Float the_new_production_b;
    private Float the_new_tax_b;
    private Float net_amount_b;
    private Float all_production_year;
    private Float the_new_production_year;
    private Float the_new_tax_year;
    private Float net_amount_year;

}
