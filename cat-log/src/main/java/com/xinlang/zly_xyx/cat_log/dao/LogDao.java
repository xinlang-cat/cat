package com.xinlang.zly_xyx.cat_log.dao;

import java.util.List;
import java.util.Map;

import com.xinlang.zly_xyx.log.Log;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;



@Mapper
public interface LogDao {

	@Insert("insert into t_log(username, module, params, remark, flag, createTime) values(#{username}, #{module}, #{params}, #{remark}, #{flag}, #{createTime})")
	int save(Log log);

	int count(Map<String, Object> params);

	List<Log> findData(Map<String, Object> params);
}
