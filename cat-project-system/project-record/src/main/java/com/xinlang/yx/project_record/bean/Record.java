package com.xinlang.yx.project_record.bean;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 *
 */
@Data
@Table(name = "project_journal")
public class Record implements Serializable {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;

    /**
     * 指标id
     */
    private Integer targetId;
    /**
     * 日志标题
     */
    private String title;
    /**
     * 日志内容
     */
    private String content;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 审核人id
     */
    private Long checkUserId;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 实施时间
     */
    private Date workingDay;
    /**
     * 创建人
     */
    private Long createUserId;
    /**
     * 备注
     */
    private String remark;


}
