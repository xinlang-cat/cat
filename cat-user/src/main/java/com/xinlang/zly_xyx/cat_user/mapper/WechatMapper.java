package com.xinlang.zly_xyx.cat_user.mapper;

import com.xinlang.zly_xyx.user.WechatUserInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019/7/22
 */
@Mapper
@Repository("wechatMapper")
public interface WechatMapper {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into t_wechat(openid, unionid, userId, app, nickname, sex, province, city, country, headimgurl, createTime, updateTime) " +
            "values(#{openid}, #{unionid}, #{userId}, #{app}, #{nickname}, #{sex}, #{province}, #{city}, #{country}, #{headimgurl}, #{createTime}, #{updateTime})")
    int save(WechatUserInfo info);

    @Select("select * from t_wechat t where t.openid = #{openid}")
    WechatUserInfo findByOpenid(String openid);

    @Select("select * from t_wechat t where t.unionid = #{unionid}")
    Set<WechatUserInfo> findByUniond(String unionid);

    int update(WechatUserInfo info);
}
