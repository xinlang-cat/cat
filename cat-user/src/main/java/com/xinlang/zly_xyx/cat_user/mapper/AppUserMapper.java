package com.xinlang.zly_xyx.cat_user.mapper;

import com.xinlang.zly_xyx.user.AppUser;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019/7/22
 */
@Mapper
@Repository("appUserMapper")
public interface AppUserMapper {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into app_user(username, password, nickname, headImgUrl, phone, sex, enabled, type, createTime, updateTime) "
            + "values(#{username}, #{password}, #{nickname}, #{headImgUrl}, #{phone}, #{sex}, #{enabled}, #{type}, #{createTime}, #{updateTime})")
    int save(AppUser appUser);

    int update(AppUser appUser);

    @Deprecated
    @Select("select * from app_user t where t.username = #{username}")
    AppUser findByUsername(String username);

    @Select("select * from app_user t where t.id = #{id}")
    AppUser findById(Long id);

    @Select("select id,nickname,headImgUrl from app_user")
    List<AppUser> findAll();
    @Select("<script>select id,nickname,headImgUrl from app_user where id in  " +
            "<foreach item='item' index='index' collection='ids' open='(' separator=',' close=')'> #{item} </foreach></script>")
    List<AppUser> findListByIds(@Param("ids") List<Integer> ids);
    int count(Map<String, Object> params);
    int count1(Map<String, Object> params);

    List<AppUser> findData(Map<String, Object> params);
    List<AppUser> findData1(Map<String, Object> params);
}
