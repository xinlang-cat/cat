package com.xinlang.cat_project.item.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;

/**
 * 项目
 * @author 杨珣
 * 2020/2/4
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "audit_apply")
public class auditApply implements Serializable {

    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    private String item_id;
    private String target_id;
    private String content;
    private String description;
    private String accessory;
    /**
     * 查定目的
     */
    private String purpose;
    /**
     * 查定依据
     */
    private String depand;
    /**
     * 查定内容
     */
    private String details;
    /**
     * 查定方法
     */
    private String means;
    /**
     * 提交方法
     * 1 pc
     * 2 微信
     */
    private Integer post_type;


    private Date start_date;
    private Date end_date;
    private Date start_date_true;
    private Date end_date_true;
    private Date edit_date;
    private Date check_date;
    private Integer edit_userid;
    private Integer check_userid;
    private Integer status;

    private String check_unit;     //审核单位
    private String manage_unit;     //管理单位

    @Transient
    private String period;


    @Transient
    private String periods;
}
