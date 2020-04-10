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
     * 0 "待审核";
     * 1 "更改中";
     * 2 "审核未通过";
     * 3 "更改成功";
     */
    private Integer status;
    private String check_unit;     //审核单位
    private String manage_unit;     //管理单位
}
