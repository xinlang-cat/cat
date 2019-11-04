package com.xinlang.zly.map.bean;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import java.util.Date;
import javax.persistence.*;
@Data
@Table(name = "bs_province")
public class Province {
    /**
     * 自增列
     */
    @KeySql(useGeneratedKeys = true)
    private Integer provinceId;

    /**
     * 省份代码
     */
    private String provinceCode;

    /**
     * 省份名称
     */
    private String provinceName;

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