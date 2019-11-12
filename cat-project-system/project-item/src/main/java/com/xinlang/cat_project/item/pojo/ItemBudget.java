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
@Table(name = "item_budget")
public class ItemBudget {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    private Integer fund_id;
    private Integer item_id;
    private String subject;
    private Float money;
    private String content;
}
