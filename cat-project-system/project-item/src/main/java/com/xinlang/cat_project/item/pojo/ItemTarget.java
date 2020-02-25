package com.xinlang.cat_project.item.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;
import java.util.List;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "item_target")
public
class ItemTarget {

    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    private Integer item_id;
    private String target;
    private Integer count;
    private String content;
    private Integer district;
    private Date start_date;
    private Date end_date;
    private String type;
    private Integer status;
    private String plan;
    @Transient
    private String userIds;
}
