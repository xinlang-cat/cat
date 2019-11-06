package com.xinlang.zly.map.bean;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
@Data
@Table(name = "bs_city")
public class City implements Serializable {
    private static final long serialVersionUID = -4273584101497877891L;
    /**
     * 自增列
     */
    @KeySql(useGeneratedKeys = true)
    private Integer cityId;

    /**
     * 市代码
     */
    private String cityCode;

    /**
     * 市名称
     */
    private String cityName;

    /**
     * 简称
     */
    private String shortName;

    /**
     * 省代码
     */
    private String provinceCode;

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