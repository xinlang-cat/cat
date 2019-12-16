package com.xinlang.cat_project.item.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "item_fund")
public class ItemFund {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    private Integer item_id;
    private String source;
    private String subject;
    private Float money;
    private String remark;
    private String doc_number;
    private Integer type;
}
