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
@Table(name = "item_company")
public class ItemCompany {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    private Integer item_id;
    private String company;
    private String dept_code;
    private String deposit_bank;
    private String account_number;
    private String linkman;
    private String phone;
    private String fax;
    private String postal_code;
    private String site;
    private String email;
    private Integer type;
}
