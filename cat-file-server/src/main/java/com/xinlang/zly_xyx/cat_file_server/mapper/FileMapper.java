package com.xinlang.zly_xyx.cat_file_server.mapper;

import com.xinlang.zly_xyx.cat_file_server.bean.Album;
import com.xinlang.zly_xyx.cat_file_server.bean.File;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019/7/19
 */
@Mapper
@Repository(value = "fileMapper")
public interface FileMapper {

    @Select("select * from file_info t where t.id = #{id}")
    File getById(String id);

    @Insert("insert into file_info(id, name, isImg, contentType, size, path, url, source, createTime) "
            + "values(#{id}, #{name}, #{isImg}, #{contentType}, #{size}, #{path}, #{url}, #{source}, #{createTime})")
    int save(File fileInfo);

    @Select({"<script>", "select", "*", "from file_info", "where id in", "<foreach collection='ids' item='id' open='(' separator=',' close=')'>",
            "#{id}", "</foreach>", "</script>"})
    List<File> getByIds(@Param("ids") List<String> ids);

    @Delete("delete from file_info where id = #{id}")
    int delete(String id);

    int count(Map<String, Object> params);

    List<File> findData(Map<String, Object> params);
}
