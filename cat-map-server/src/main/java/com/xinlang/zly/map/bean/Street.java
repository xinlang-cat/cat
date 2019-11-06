package com.xinlang.zly.map.bean;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
@Data
@Table(name = "bs_street")
public class Street implements Serializable {
    private static final long serialVersionUID = 6564721458565463490L;
    /**
     * 自增列
     */
    @KeySql(useGeneratedKeys = true)
    private Integer streetId;

    /**
     * 街道代码
     */
    private String streetCode;

    /**
     * 父级区代码
     */
    private String areaCode;

    /**
     * 街道名称
     */
    private String streetName;

    /**
     * 简称
     */
    private String shortName;

    /**
     * 经度
     */
    private String lng;

    /**
     * 纬度
     */
    private String lat;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 修改时间
     */
    private Date gmtModified;

    /**
     * 备注
     */
    private String memo;

    /**
     * 状态
     */
    private Integer dataState;

    /**
     * 租户ID
     */
    private String tenantCode;

}