package com.xinlang.zly.summary.bean;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019-11-11
 */
@Data
@Table(name = "project_summary")
public class ProjectSummary implements Serializable {
    private static final long serialVersionUID = 1340653261208252531L;
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    private Integer userId;
    private Integer itemId;

}
