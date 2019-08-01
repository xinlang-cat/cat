package com.xinlang.zly_xyx.cat_manage_backend.dao;


import com.xinlang.zly_xyx.email.Email;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface MailDao {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into t_mail(userId, username, toEmail, subject, content, status, createTime, updateTime) values(#{userId}, #{username}, #{toEmail}, #{subject}, #{content}, #{status}, #{createTime}, #{updateTime})")
    int save(Email mail);

    int update(Email mail);

    @Select("select * from t_mail t where t.id = #{id}")
    Email findById(Long id);

    int count(Map<String, Object> params);

    List<Email> findData(Map<String, Object> params);
}
