package com.xinlang.zly.label.bean;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019-10-31
 */
@Data
@Table(name = "project_label")
public class Label implements Serializable {
    private static final long serialVersionUID = 1511493084535163722L;
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    private Integer pid;//父级ID
    private String sign;//标记
    private String content;//标签
    private Integer sort;//排序
    private Integer enabled;//0不启用，1启用，默认启用
    @Transient
    private List<Label> child;//子集
    private String unit;//计量单位
    /**
     * 保留字段，暂时不用
     */
    private Integer type;//0：标签，1：字典，2：计量单位
    private Date createTime;
    private Date updateTime;
}
