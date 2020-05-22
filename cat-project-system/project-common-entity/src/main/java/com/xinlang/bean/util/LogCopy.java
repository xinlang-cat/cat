package com.xinlang.bean.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.annotations.Result;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 日志对象
 */

@Data
@Table(name = "t_log")
public class LogCopy implements Serializable{
	@Id
	@KeySql(useGeneratedKeys = true)
	private Long id;
	/** 用户名 */
	private String username;
	/** 模块 */
	private String module;
	/** 参数值 */
	private String params;
	private String remark;
	/** 是否执行成功 */
	private Boolean flag;
	@Column(name = "createTime")
	private Date createTime;
}
