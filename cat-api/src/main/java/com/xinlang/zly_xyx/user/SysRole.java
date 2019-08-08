package com.xinlang.zly_xyx.user;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 角色
 * @author 张龙毅 18777811286@163.com
 * 2019/7/22
 */
@Data
public class SysRole implements Serializable {

	private static final long serialVersionUID = 6974660101528818052L;

	private Long id;
	private String code;
	private String name;
	private Date createTime;
	private Date updateTime;
}
