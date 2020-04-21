package com.xinlang.cat_project.item.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "item_information")
public class ItemInformation implements Serializable {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    private String contract_no;         //合同编号
    private String type;                //项目类型
    private String name;                //项目名称
    private String entrusting_party;    //委托单位
    private String responsible_unit;    //承担单位
    private String management_unit;     //管理单位
    private String document_number;     //下大文号
    private String overall_objective;   //总体目标
    private String research_contents;   //主要研究内容
    private Date start_date;            //开始日期
    private Date end_date;              //结束日期
    private String accessory;           //附件
    private Date edit_date;             //编辑时间
    private Integer edit_user_id;        //编辑人ID
    private Integer status;             //状态，默认为0
    private String province_code;
    private String city_code;
    private String area_code;
    private String street_code;

    @Transient
    private String period;
}
