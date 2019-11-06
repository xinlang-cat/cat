package com.xinlang.zly.map.bean;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Table;

@Data
@Table(name = "bs_area")
public class Area implements Serializable {
    private static final long serialVersionUID = 8924707387081403806L;
    /**
     * 自增列
     */
    @KeySql(useGeneratedKeys = true)
    private Integer areaId;

    /**
     * 区代码
     */
    private String areaCode;

    /**
     * 父级市代码
     */
    private String cityCode;

    /**
     * 市名称
     */
    private String areaName;

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