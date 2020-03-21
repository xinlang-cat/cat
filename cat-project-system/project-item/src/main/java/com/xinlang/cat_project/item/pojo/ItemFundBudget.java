package com.xinlang.cat_project.item.pojo;

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
@Table(name = "item_fund_budget")
public class ItemFundBudget {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    private Integer item_id;
    private String subject;
    private Integer money;
    private String content;
    private String remark;
    private String type;
}
