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
}
