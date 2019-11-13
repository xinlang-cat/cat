package com.xinlang.bean.company;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@Table(name="company")
public class Company implements Serializable {

    private static final long serialVersionUID = -5939563375899793641L;

    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;

    /**
     * 公司名称
     */
    private String signName;

    /**
     * 详细地址
     */
    private String address;

    /**
     * 公司成立日期
     */
    private Date registerDate;

    /**
     * 负责人号码
     */
    private String principalPhone;

    /**
     * 负责人
     */
    private String principal;

    /**
     * 法人
     */
    private String legalPerson;

    /**
     * 法人号码
     */
    private String legalPersonPhone;

    /**
     * 法人身份证
     */
    private String legalPersonCard;

    /**
     * 公司号码
     */
    private String telephone;

    /**
     * 注册资本(万）
     */
    private Integer registeredCapital;

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
    private String deptCode;

    /**
     * 资质等级
     */
    private Boolean credential;

    /**
     * 资质证书编号
     */
    private Integer credentialNumber;

    /**
     * 发证机构
     */
    private String sendDept;

    /**
     * 发证日期
     */
    private Date sendDate;

    /**
     * 有效期(年）
     */
    private Integer validTime;

    private Date createTime;

    private Date updateTime;

    /**
     * 状态
     */
    private Boolean enabled;
}