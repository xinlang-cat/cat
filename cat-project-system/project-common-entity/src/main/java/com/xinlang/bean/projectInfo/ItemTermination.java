package com.xinlang.bean.projectInfo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "item_termination")
public class ItemTermination {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    private Integer item_id;
    private String remark;
    private String accessorys;
    private Integer status;
    private String manage_dept_opinion;
    private String burg_leader_opinion;
    private String burg_finance_opinion;
    private String manage_dept_result;
    private String acceptance_company; //验收单位名
}
