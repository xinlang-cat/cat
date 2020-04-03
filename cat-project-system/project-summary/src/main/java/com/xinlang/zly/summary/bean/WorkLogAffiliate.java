package com.xinlang.zly.summary.bean;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@Table(name="project_work_log_affiliate")
public class WorkLogAffiliate implements Serializable {
    private static final long serialVersionUID = -5608505454072482751L;
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    private Integer itemId;
    private Integer workLogId;
    private Integer originalCount;//计划数量
    private Integer oldCount;//累计到上次
    private Integer nowCount;//本次
    private String plan;//进度百分比
    private String content;//指标内容
    private Date updateTime;
    private Date createTime;
}
