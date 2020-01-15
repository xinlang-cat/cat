package com.xinlang.zly.summary.bean;

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
@Table(name = "project_work_log")
public class WorkLog implements Serializable {

    private static final long serialVersionUID = -5320384153350355178L;
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    private Integer targetId;//指标id
    private Integer createUserId;//创建人id
    private String createUserName;//创建人名字
    private Integer itemId;//项目id
    private String plan;//完成进度百分比
    private String content;//日志内容
    private Integer status;//状态
    private Date createTime;//创建时间
    private String accessory;//附件
    private Date implementTime;//实施时间
    private String remark;//备注
}
