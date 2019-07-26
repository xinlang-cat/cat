package com.xinlang.zly_xyx.cat_inform.Mapper;

import com.xinlang.zly_xyx.cat_inform.bean.Sms;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019/7/26
 */
@Mapper
@Repository("smsMapper")
public interface SmsMapper {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into t_sms(phone, signName, templateCode, params, day, createTime, updateTime) "
            + "values(#{phone}, #{signName}, #{templateCode}, #{params}, #{day}, #{createTime}, #{updateTime})")
    int save(Sms sms);

    @Select("select * from t_sms t where t.id = #{id}")
    Sms findById(Long id);

    int update(Sms sms);

    int count(Map<String, Object> params);

    List<Sms> findData(Map<String, Object> params);
}
