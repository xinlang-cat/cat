package com.xinlang.bean.project_user;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
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

    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    private Integer userId;//系统用户表id
    private String sex;//性别
    private String name;//姓名
    private Date birthDate;//出生日期
    private String nation;//民族
    private String idType;//证件类型
    private String idCard;//证件号码
    private String stateCode;//国家地区编码
    private String provinceCode;//省份编码
    private String cityCode;//城市编码
    private String areaCode;//区县编码
    private String streetCode;//街道镇编码
    private String deptCode;//所在单位公司编码
    private String deptName;//单位公司名称
    private String deptType;//单位公司级别(标签库编码)
    private String academicTitle;//职称
    private String politics;//政治面貌
    private String job;//职务
    private String academicTitleRank;//职称级别
    private String graduatedFrom;//毕业院校
    private String address;//通讯地址
    private String degree;//学位
    private String academicDiplomas;//学历
    private Date lastYear;//学位授予年份
    private String degreeState;//学位授予国别，及地区
    private String degreeSchool;//最高学位授予学校
    private String email;//邮箱
    private String major;//所学专业
    private String telephone;//办公电话
    private String nowMajor;//现在从事专业
    private String phone;//手机号码
    private String mainDomain;//主要研究领域
    private String fax;//传真
    @Transient
    private List<ProjectUserDomain> domains;//服务的产业或领域
    private String postcode;//邮政编码
    @Transient
    private List<ProjectUserSkill> skills;//技术专长及提供的服务
    private String projects;//承担的科技项目
    private String achievement;//获得的科技成果，奖励，荣誉
    private String accomplishment;//工作成就案例
    private Date createTime;
    private Date updateTime;
    private Boolean enable;//是否可用/0不可用/1可用
    private String userType;//用户类型
    @Transient
    private Integer time;//选中次数
    @Transient
    private Date endServerTime;//最后一次服务时间


}