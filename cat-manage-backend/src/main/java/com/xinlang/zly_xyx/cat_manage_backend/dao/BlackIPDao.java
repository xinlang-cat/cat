package com.xinlang.zly_xyx.cat_manage_backend.dao;

import java.util.List;
import java.util.Map;

import com.xinlang.zly_xyx.cat_manage_backend.model.BlackIP;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;



@Mapper
public interface BlackIPDao {

	@Insert("insert into black_ip(ip, createTime) values(#{ip}, #{createTime})")
	int save(BlackIP blackIP);

	@Delete("delete from black_ip where ip = #{ip}")
	int delete(String ip);

	@Select("select * from black_ip t where t.ip = #{ip}")
	BlackIP findByIp(String ip);

	int count(Map<String, Object> params);

	List<BlackIP> findData(Map<String, Object> params);
}
