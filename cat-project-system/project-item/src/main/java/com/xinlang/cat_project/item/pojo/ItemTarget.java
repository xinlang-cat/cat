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
    private Integer content_id;
    private String target;
    private String remark;
    private String unit;
    private Integer district;
    private Date start_date;
    private Date end_date;
    private Integer type;
    private Integer status;

    @Transient
    private List<Integer> userIds;

    @Transient
    private String start_dateStr;
    @Transient
    private String end_dateStr;

}
