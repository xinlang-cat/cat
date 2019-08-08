package com.xinlang.zly_xyx.user;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 权限标识
 * @author 张龙毅 18777811286@163.com
 * 2019/7/22
 */
@Data
public class SysPermission implements Serializable {


	private static final long serialVersionUID = 3728200727363637255L;
	private Long id;
	private String permission;
	private String name;
	private Date createTime;
	private Date updateTime;

}
