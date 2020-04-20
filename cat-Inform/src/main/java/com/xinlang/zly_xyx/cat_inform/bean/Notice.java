package com.xinlang.zly_xyx.cat_inform.bean;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@Table(name="sys_notice")
public class Notice implements Serializable {
    private static final long serialVersionUID = -3552838170725620578L;
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    private String title;//标题
    private String content;//内容
    private Integer createUserId;//创建者id
    private String createUserNickName;//创建者昵称
    private String pageName;//要发布在的页面
    private Date createTime; //创建时间
 }
