package com.xinlang.zly_xyx.user;


import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Table(name="customer_service_staff")
public class CustomerServiceStaff implements Serializable {
    private static final long serialVersionUID = 3728200737363637256L;
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    private Integer userId;
    private String userName;//昵称
    private String headImgUrl;//头像
}
