package com.xinlang.zly_xyx.company.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Company implements Serializable {

    private static final long serialVersionUID = -5939563375899793641L;

    private Integer id;

    /**
     * 公司名称
     */
    private String name;

    /**
     * 详细地址
     */
    private String address;

    /**
     * 公司成立日期
     */
    private Date registerdate;

    /**
     * 负责人号码
     */
    private String principalphone;

    /**
     * 负责人
     */
    private String principal;

    /**
     * 法人
     */
    private String legalperson;

    /**
     * 法人号码
     */
    private String legalpersonphone;

    /**
     * 法人身份证
     */
    private String legalpersoncard;

    /**
     * 公司号码
     */
    private String telephone;

    /**
     * 注册资本(万）
     */
    private Integer registeredcapital;

    /**
     * 营业执照
     */
    private String charter;

    /**
     * 行业‘>'号分隔
     */
    private String trade;

    /**
     * 企业类型
     */
    private Boolean type;

    /**
     * 组织机构代码
     */
    private String deptcode;

    /**
     * 资质等级
     */
    private Boolean credential;

    /**
     * 资质证书编号
     */
    private Integer credentialnumber;

    /**
     * 发证机构
     */
    private String senddept;

    /**
     * 发证日期
     */
    private Date senddate;

    /**
     * 有效期(年）
     */
    private Integer validtime;

    private Date createtime;

    private Date updatetime;

    /**
     * 状态
     */
    private Boolean enabled;
}