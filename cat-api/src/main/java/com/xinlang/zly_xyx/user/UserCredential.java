package com.xinlang.zly_xyx.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 用户账号类型
 * @author 张龙毅 18777811286@163.com
 * 2019/7/22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCredential implements Serializable {


	private static final long serialVersionUID = 6913908773164767286L;
	private String username;
	/**
	 * @see com.xinlang.zly_xyx.user.constants.CredentialType
	 */
	private String type;
	private Long userId;

}
