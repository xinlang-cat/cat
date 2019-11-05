package com.xinlang.cat_project.item.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;

/**
 * 项目
 * @author 梁应昌
 * 2019/9/20
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "item_basic")
public class ItemBasic implements Serializable {

    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    private String contract_number;
    private String plan_category;
    private String item_name;
    private String batch;
    private String consignor;
    private String undertaker;
    private String supervisor_dept;
    private String supervisor;
    private String overall_objective;
    private Integer district;
    private String contract_file;
    private Date start_date;
    private Date end_date;
    private Date edit_date;
    private Integer edit_userid;
    private Integer status;

    @Transient
    private String start_dateStr;
    @Transient
    private String end_dateStr;
    @Transient
    private String edit_dateStr;

}
