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
    private String signName;//公司名称
    private String provinceCode;
    private String cityCode;
    private String areaCode;
    private String streetCode;
    private String address;//详细地址
    private Date registerDate;//公司成立日期
    private String principalPhone;//负责人号码
    private String principal;//负责人
    private String legalPerson;//法人
    private String legalPersonPhone;//法人号码
    private String legalPersonCard;//法人身份证
    private String telephone;//公司号码
    private Integer registeredCapital;//注册资本(万）
    private String charter;//营业执照
    private String trade;//行业‘>'号分隔
    private String type;//企业类型
    private String identity; //身份类型
    private String deptCode;//组织机构代码
    private String credential;//资质等级
    private Integer credentialNumber;//资质证书编号
    private String sendDept;//发证机构
    private Date sendDate;//发证日期
    private Integer validTime;//有效期(年）
    private Date createTime;
    private Date updateTime;
    private Boolean enabled;//状态
}