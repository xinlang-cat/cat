package com.xinlang.zly_xyx.cat_file_server.mapper;

import com.xinlang.zly_xyx.cat_file_server.bean.Album;
import com.xinlang.zly_xyx.cat_file_server.bean.File;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019/7/19
 */
@Mapper
@Repository(value = "albumMapper")
public interface AlbumMapper {

    @Select("select * from album t where t.id = #{id}")
    Album getById(String id);

    @Select({"<script>", "select", "*", "from album", "where id in", "<foreach collection='ids' item='id' open='(' separator=',' close=')'>",
            "#{id}", "</foreach>", "</script>"})
    List<Album> getByIds(@Param("ids") List<String> ids);

    @Insert("insert into album(id, name, userId, isWeChat, contentType, size, path, url, source, createTime) "
            + "values(#{id}, #{name}, #{userId}, #{isWeChat}, #{contentType}, #{size}, #{path}, #{url}, #{source}, #{createTime})")
    int save(Album album);

    @Delete("delete from album where id = #{id}")
    int delete(String id);

    int count(Map<String, Object> params);

    List<Album> findData(Map<String, Object> params);
}
