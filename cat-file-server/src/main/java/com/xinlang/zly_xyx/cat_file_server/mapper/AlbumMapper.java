package com.xinlang.zly_xyx.cat_file_server.mapper;

import com.xinlang.zly_xyx.cat_file_server.bean.Album;
import com.xinlang.zly_xyx.cat_file_server.bean.File;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019/7/19
 */
@Mapper
@Repository(value = "albumMapper")
public interface AlbumMapper {

    @Select("select * from album t where t.id = #{id}")
    Album getById(String id);

    @Insert("insert into album(id, name, userId, isWeChat, contentType, size, path, url, source, createTime) "
            + "values(#{id}, #{name}, #{userId}, #{isWeChat}, #{contentType}, #{size}, #{path}, #{url}, #{source}, #{createTime})")
    int save(Album album);

    @Delete("delete from album where id = #{id}")
    int delete(String id);

    int count(Map<String, Object> params);

    List<Album> findData(Map<String, Object> params);
}
