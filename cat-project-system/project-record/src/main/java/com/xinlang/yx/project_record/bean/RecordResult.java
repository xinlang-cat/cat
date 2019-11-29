package com.xinlang.yx.project_record.bean;

import lombok.Data;

import java.util.Date;

/**
 * @author 杨珣
 * @date 2019-11-22
 */
@Data
public class RecordResult {
    private Integer id;

    /**
     * 指标id
     */
    private Integer targetId;
    /**
     * 项目id
     */
    private Integer projectId;
    /**
     * 项目id
     */
    private String username;
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
     * 审核人
     */
    private String checkUser;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 实施时间
     */
    private Date workingDay;
    /**
     * 创建人id
     */
    private Long createUserId;
    /**
     * 创建人
     */
    private String createUser;
    /**
     * 备注
     */
    private String remark;

    private String time;

    private String state;
}
