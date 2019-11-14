package com.xinlang.yx.project_record.service.impl;

import com.xinlang.yx.project_record.VO.RecordVO;
import com.xinlang.yx.project_record.bean.Record;
import com.xinlang.yx.project_record.mapper.RecordMapper;
import com.xinlang.yx.project_record.service.IRecordService;
import com.xinlang.yx.project_record.utils.constant;
import com.xinlang.zly_xyx.cat_common.utils.AppUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * @author 杨珣
 * @date 2019-11-12
 */
@Service
@Transactional
public class RecordService implements IRecordService {
    @Autowired
    private RecordMapper recordMapper;

    @Override
    public void insert(Record record) {
        Long id = AppUserUtil.getLoginAppUser().getId();
        record.setCreateUserId(id);
        record.setStatus(constant.ConstantStatus.UN_SUBMIT);
        record.setCreateTime(new Date());
        recordMapper.insert(record);

    }

    @Override
    public void update(Record record) {

        recordMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public void delete(Integer id) {
        recordMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void check(Integer id, Integer status) {
        Record journal = new Record();
        journal.setStatus(status);
        Example example = new Example(Record.class);

        example.createCriteria().andEqualTo("id",id);
        recordMapper.updateByExampleSelective(journal,example);

    }

    @Override
    public List<Record> findByTarIdAUID(Integer targetId) {
        Long userId = AppUserUtil.getLoginAppUser().getId();
        Example example = new Example(Record.class);
        example.createCriteria().andEqualTo("targetId",targetId);
        example.createCriteria().andEqualTo("createUserId",userId);
        return recordMapper.selectByExample(example);
    }

    @Override
    public List<Record> findByTarIdAUIDASTU(Integer targetId, Integer unSubmit) {
        Long userId = AppUserUtil.getLoginAppUser().getId();
        Example example = new Example(Record.class);
        example.createCriteria().andEqualTo("targetId",targetId);
        example.createCriteria().andEqualTo("createUserId",userId);
        example.createCriteria().andEqualTo("status",unSubmit);
        return recordMapper.selectByExample(example);
    }

    @Override
    public List<RecordVO> findByTarIdAUIDASTUVO(Integer targetId, Integer checkPass) {
        return recordMapper.findByTarIdAUIDASTUVO(targetId, checkPass);
    }

    @Override
    public RecordVO findVOById(Integer id) {

        return recordMapper.findVO(id);
    }

    @Override
    public void addFile(List<Integer> fileIds,Integer id) {
        Integer fileId;
        for (int i=0;i<fileIds.size();i++){
            fileId=fileIds.get(i);
            recordMapper.addFile(id,fileId);
        }
    }

    @Override
    public void delFile(List<Integer> fileIds, Integer id) {
        Integer fileId;
        for (int i=0;i<fileIds.size();i++){
            fileId=fileIds.get(i);
            recordMapper.delFile(id,fileId);
        }
    }


}
