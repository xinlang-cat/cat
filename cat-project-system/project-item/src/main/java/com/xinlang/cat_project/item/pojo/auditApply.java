package com.xinlang.cat_project.item.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
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
    private String provinceCode;
    private String cityCode;
    private String areaCode;
    private String streetCode;
    private String description;
    private String accessory;

    private Date start_date;
    private Date end_date;
    private Date start_date_true;
    private Date end_date_true;
    private Date edit_date;
    private Date check_date;
    private Integer edit_userid;
    private Integer check_userid;
    private Integer status;
}
