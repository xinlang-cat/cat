package com.xinlang.yx.project_report.bean;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @author 杨珣
 * @date 2019-11-13
 */
@Data
@Table(name="project_report")
public class Report implements Serializable {

    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;//id

    private Integer projectId;//项目id

    private String title;//报告标题

    private String content;//报告内容

    private Date createTime;//创建时间

    private Long createUserId;//创建人id

    private Integer status;//状态

    private Date startDay;//开始时间

    private Date finishDay;//结束时间
}
