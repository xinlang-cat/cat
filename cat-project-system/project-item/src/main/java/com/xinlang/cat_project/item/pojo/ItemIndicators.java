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

/*项目考核指标*/

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "item_indicators")
public class ItemIndicators {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    private Integer item_id;
    private String type;
    private String content;
    private Integer count;
    private String site;
    private Date start_date;
    private Date end_date;
    private Integer status;

    @Transient
    private String period;
}
