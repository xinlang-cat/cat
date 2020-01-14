package com.xinlang.zly.summary.bean;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019-12-30
 */
@Data
@Table(name = "project_stage_summary")
public class Stage implements Serializable {

    private static final long serialVersionUID = -4349370566893021101L;

    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;//id
    private Integer itemId;//项目id
    private Integer targetId;//指标id
    private Integer type;//类型0，阶段报告，1附件
    private Date createTime;//创建时间
    private String path;//路径
    private Integer createUserId;//创建人id
    private String createUserName;//创建人姓名
    private Integer enable;//状态
    private String remark;//备注

}
