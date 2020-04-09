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
@Table(name = "item_contact_way")
public class ItemContactWay {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    private Integer item_id;
    private Integer leader;
    private String leader_phone;
    private String linkman;
    private String linkman_phone;
    private String fax;
    private String postcode;
    private String site;
    private String e_mail;
    private Integer type;
}
