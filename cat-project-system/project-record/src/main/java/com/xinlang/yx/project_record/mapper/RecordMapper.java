package com.xinlang.yx.project_record.mapper;

import com.xinlang.yx.project_record.VO.RecordVO;
import com.xinlang.yx.project_record.bean.Record;
import com.xinlang.yx.project_record.bean.RecordResult;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 杨珣
 * @date 2019-11-12
 */
@Mapper
@Repository("recordMapper")
public interface RecordMapper extends tk.mybatis.mapper.common.Mapper<Record> {

    List<RecordVO> findByTarIdAUIDASTUVO(@Param("targetId") Integer targetId, @Param("status") Integer status);

    RecordVO findVO(@Param("id") Integer id);

    void addFile(@Param("id") Integer id, @Param("fileId") Integer fileId);

    void delFile(@Param("id") Integer id, @Param("fileId") Integer fileId);

    List<Record> findByproId(@Param("proId") Integer proId, @Param("status") Integer status, @Param("userId") Long userId);

    List<RecordResult> find(@Param("proId") Integer proId, @Param("status") Integer status, @Param("userId") Long userId);
}
