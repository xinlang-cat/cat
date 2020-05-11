package com.xinlang.bean.message;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

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

    public Message(String createUserName, String content, String title, Set<Integer> userIds){
        this.createUserName = createUserName;
        this.content = content;
        this.title = title;
        this.createTime = new Date();
        this.userIds = userIds;
        this.type = "系统通知";
    }

}
