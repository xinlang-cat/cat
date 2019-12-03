package com.xinlang.zly.summary.bean;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019-12-03
 * 技术总结
 */
@Data
@Table(name = "project_summary_skill")
public class SkillSummary implements Serializable {
    private static final long serialVersionUID = -7328383884683565140L;
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    private Integer itemId;//项目id
    private Integer createUserId;//创建人id
    private String createUserName;//创建人姓名
    private String projectContent;//项目背景
    private String researchContents;//研究内容
    private String skillRoute;//技术路线
    private String skillInnovate;//技术创新点
    private String skillIndicator;//项目技术指标完成情况
    private String skillEvaluate;//项目技术评价
    private String projectEffect;//项目实施对企业的影响
    private Date createTime;
    private Date updateTime;
}