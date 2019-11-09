package com.xinlang.bean.project_user;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 用户信息
 */
@Data
@Table(name = "project_user")
public class ProjectUser implements Serializable {

    private static final long serialVersionUID = -5560807862082312933L;

    @KeySql(useGeneratedKeys = true)
    private Integer id;

    /**
     * 系统用户表id
     */
    private Integer userId;

    /**
     * 性别
     */
    private String sex;

    /**
     * 姓名
     */
    private String name;

    /**
     * 出生日期
     */
    private Date birthDate;

    /**
     * 民族
     */
    private String nation;

    /**
     * 证件类型
     */
    private String idType;

    /**
     * 证件号码
     */
    private String idCard;

    /**
     * 国家地区编码
     */
    private String stateCode;

    /**
     * 省份编码
     */
    private String provinceCode;

    /**
     * 城市编码
     */
    private String cityCode;

    /**
     * 区县编码
     */
    private String areaCode;

    /**
     * 街道镇编码
     */
    private String streetCode;

    /**
     * 所在单位公司编码
     */
    private String deptCode;

    /**
     * 单位公司名称
     */
    private String deptName;

    /**
     * 单位公司级别(标签库编码)
     */
    private String deptType;

    /**
     * 职称
     */
    private String academicTitle;

    /**
     * 政治面貌
     */
    private String politics;

    /**
     * 职务
     */
    private String job;

    /**
     * 职称级别
     */
    private String academicTitleRank;

    /**
     * 毕业院校
     */
    private String graduatedFrom;

    /**
     * 通讯地址
     */
    private String address;

    /**
     * 学位
     */
    private String degree;

    /**
     * 学历
     */
    private String academicDiplomas;

    /**
     * 学位授予年份
     */
    private Date lastYear;

    /**
     * 学位授予国别，及地区
     */
    private String degreeState;

    /**
     * 最高学位授予学校
     */
    private String degreeSchool;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 所学专业
     */
    private String major;

    /**
     * 办公电话
     */
    private String telephone;

    /**
     * 现在从事专业
     */
    private String nowMajor;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 主要研究领域
     */
    private String mainDomain;

    /**
     * 传真
     */
    private String fax;

    /**
     * 服务的产业或领域
     */
    @Transient
    private List<ProjectUserDomain> domains;

    /**
     * 邮政编码
     */
    private String postcode;

    /**
     * 技术专长及提供的服务
     */
    @Transient
    private List<ProjectUserSkill> skills;

    /**
     * 承担的科技项目
     */
    private String projects;

    /**
     * 获得的科技成果，奖励，荣誉
     */
    private String achievement;

    /**
     * 工作成就案例
     */
    private String accomplishment;

    private Date createTime;

    private Date updateTime;

    /**
     * 是否可用/0不可用/1可用
     */
    private Boolean enable;

    /**
     * 用户类型
     */
    private String userType;
}