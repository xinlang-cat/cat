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
@Table(name = "item_user")
public class ItemUser {

    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    private Integer pid;
    private Integer user_id;
    private Integer item_id;
    private Integer target_id;
    private String responsible;
    private Integer type;
}
