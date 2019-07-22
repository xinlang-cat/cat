package com.xinlang.zly_xyx.user;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
/**
 * @author 张龙毅 18777811286@163.com
 * 2019/7/22
 */
@Data
public class AppUser implements Serializable {

	private static final long serialVersionUID = 611197991672067628L;

	private Long id;
	private String username;
	private String password;
	private String nickname;
	private String headImgUrl;
	private String phone;
	private Integer sex;
	/**
	 * 状态
	 */
	private Boolean enabled;
	private String type;
	private Date createTime;
	private Date updateTime;

}
