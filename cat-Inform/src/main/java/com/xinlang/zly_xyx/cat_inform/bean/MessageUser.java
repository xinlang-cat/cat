package com.xinlang.zly_xyx.cat_inform.bean;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@Table(name = "sys_message_user")
public class MessageUser implements Serializable {
    private static final long serialVersionUID = 1754543494930414467L;
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    private Integer userId;
    private Integer messageId;
    private Integer isRead;
    private Date readTime;

}
