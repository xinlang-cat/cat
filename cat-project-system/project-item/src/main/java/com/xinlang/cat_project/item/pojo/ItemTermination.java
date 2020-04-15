package com.xinlang.cat_project.item.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Table;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "item_termination")
public class ItemTermination {
    private Integer id;
    private Integer item_id;
    private String remark;
    private String accessorys;
    private Integer status;
}
