package com.xinlang.yx.project_record.service;

import com.xinlang.yx.project_record.VO.RecordVO;
import com.xinlang.yx.project_record.bean.Record;
import com.xinlang.yx.project_record.bean.RecordResult;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author 杨珣
 * @date 2019-11-12
 */
public interface IRecordService {
    void insert(Record record);

    void update(Record record);

    void delete(Integer id);

    void check(Integer id, Integer checkIn);

    List<Record> findByTarIdAUID(Integer targetId);

    List<RecordVO> findByTarIdAUIDASTUVO(Integer targetId, Integer checkPass);

    RecordVO findVOById(Integer id);

    void addFile(List<Integer> fileIds,Integer id);

    void delFile(List<Integer> fileIds, Integer id);

    List<Record> findByproId(Integer proId,Integer status,Integer wetherUser);

    List<Record> findByTarIdAUIDASTU(Integer targetId, Integer checkPass);

    List<RecordResult> find(Integer proId, Integer status, Integer weatherUser);

    List<RecordResult> findPass(Integer proId);

    List<RecordResult> findAll(Integer proId);

    List<RecordResult> findCheck(Integer proId);
}
