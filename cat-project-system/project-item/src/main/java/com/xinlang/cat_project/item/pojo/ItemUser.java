package com.xinlang.cat_project.item.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "item_user")
public class ItemUser {

    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    private Integer user_id;
    private Integer item_id;
    private Integer type;
    @Transient
    private List<Integer> targetIds;

}
