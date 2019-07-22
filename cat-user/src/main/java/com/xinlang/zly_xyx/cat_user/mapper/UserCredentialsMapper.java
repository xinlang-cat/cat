package com.xinlang.zly_xyx.cat_user.mapper;

import com.xinlang.zly_xyx.user.AppUser;
import com.xinlang.zly_xyx.user.UserCredential;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019/7/22
 */
@Mapper
@Repository("userCredentialsMapper")
public interface UserCredentialsMapper {

	@Insert("insert into user_credentials(username, type, userId) values(#{username}, #{type}, #{userId})")
	int save(UserCredential userCredential);

	@Select("select * from user_credentials t where t.username = #{username}")
	UserCredential findByUsername(String username);

	@Select("select u.* from app_user u inner join user_credentials c on c.userId = u.id where c.username = #{username}")
	AppUser findUserByUsername(String username);
}
