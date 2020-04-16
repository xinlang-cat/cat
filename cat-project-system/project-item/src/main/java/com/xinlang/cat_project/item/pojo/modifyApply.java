package com.xinlang.cat_project.item.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 项目
 * @author 杨珣
 * 2020/2/4
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "modify_apply")
public class modifyApply implements Serializable {

    @Id
    @KeySql(useGeneratedKeys = true)

    private Integer id;
    private Integer item_id;
    /**
     *修改原因说明
     */
    private String content;
    private Integer user_id;
    /**
     *修改人id
     */
    private Integer change_user;
    /**
     * 修改类型
     * 1 "项目基本信息";
     * 2 "项目研究内容";
     * 3 "项目人员";
     * 4 "项目指标";
     * 5 "项目经费";
     */
    private Integer modify;
    /**
     *申请时间
     */
    private Date apply_time;
    /**
     *修改时间
     */
    private Date modify_time;
    /**
     *审核人id
     */
    private Integer check_user;
    /**
     *修改状态
     * 0 "未提交";
     * 1 "监理审核中";
     * 2 "监理审核通过";
     * 3 "监理审核未通过";
     * 4 "甲方审核中";
     * 5 "甲方审核通过";
     * 6 "甲方审核未通过";
     */
    private Integer status;
    private String check_unit;     //监理单位
    private String manage_unit;     //甲方单位
    /**
     *修改类型
     * 1 "项目名称";
     * 2 "承担单位（单位+负责人）";
     * 3 "承担单位（单位）";
     * 4 "参与单位变更/承担单位变更";
     * 5 "项目负责人变更";
     * 6 "资助经费总额变更";
     * 7 "实施期限变更";
     * 8 "实施单位间的经费调整变更";
     * 9 "研究内容";
     * 10 "考核指标";
     * 11 "项目组成员";
     * 12 "经费明细";
     * 13 "研究内容";
     * 14 "实施地点";
     */
    private Integer type;

    private String accessory;

    private String check_unit_idea;     //监理单位意见
    private String manage_unit_idea;     //甲方单位意见
}
