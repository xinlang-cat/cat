package com.xinlang.zly_xyx.cat_inform.bean;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.Transient;

@Data
@Table(name = "sys_message")
public class Message implements Serializable {
    private static final long serialVersionUID = -6655112945361420946L;
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    private Integer createUserId;//创建人
    private String createUserName;//创建者姓名
    private String content;//消息内容
    private String title;//消息头
    private String type = "系统通知";//通知类型
    private Date createTime;
    @Transient
    private Set<Integer> userIds;//接收人
    @Transient
    private Integer isRead;

}
