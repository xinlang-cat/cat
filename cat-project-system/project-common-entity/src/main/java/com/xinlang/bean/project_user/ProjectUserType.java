package com.xinlang.bean.project_user;

/**
 * 用户类型
 * @author 张龙毅 18777811286@163.com
 * 2019-11-09
 */
public enum ProjectUserType {

    /**
     * 甲方
     */
    PARTY_A,
    /**
     * 乙方
     */
    PARTY_B_PRINCIPAL,//项目负责人
    PARTY_B_MEMBER,//项目成员
    /**
     * 监理
     */
    PARTY_C,
    /**
     * 验收
     */
    PARTY_D,
    /**
     * 专家
     */
    EXPERT
}
