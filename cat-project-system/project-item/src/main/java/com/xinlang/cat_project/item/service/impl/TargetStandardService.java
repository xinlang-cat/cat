package com.xinlang.cat_project.item.service.impl;

import com.xinlang.cat_project.item.enums.ExceptionEnum;
import com.xinlang.cat_project.item.exception.ItemException;
import com.xinlang.cat_project.item.mapper.TargetStandardMapper;
import com.xinlang.cat_project.item.pojo.TargetStandard;
import com.xinlang.cat_project.item.service.ITargetStandardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class TargetStandardService implements ITargetStandardService {

    @Autowired
    private TargetStandardMapper targetStandardMapper;

    @Override
    public void saveTargetStandard(TargetStandard targetStandard) {
        int i = targetStandardMapper.insertSelective(targetStandard);
        if (i != 1){
            throw new ItemException(ExceptionEnum.TARGET_SAVE_ERROR);
        }
    }

    @Override
    public TargetStandard queryTargetStandardById(Integer id) {
        TargetStandard targetStandard = targetStandardMapper.selectByPrimaryKey(id);
        if(targetStandard==null){
            throw new ItemException(ExceptionEnum.DATA_NOT_FOUND);
        }
        return targetStandard;
    }

    @Override
    public List<TargetStandard> queryTargetStandardByTId(Integer tid) {
        TargetStandard targetStandard = new TargetStandard();
        targetStandard.setTarget_id(tid);
        List<TargetStandard> list = targetStandardMapper.select(targetStandard);
        if(CollectionUtils.isEmpty(list)){
            throw new ItemException(ExceptionEnum.DATA_NOT_FOUND);
        }
        return list;
    }

    @Override
    public void updateTargetStandard(TargetStandard targetStandard) {
        int i = targetStandardMapper.updateByPrimaryKeySelective(targetStandard);
        if(i != 1){
            throw new ItemException(ExceptionEnum.TARGET_UPDATE_ERROR);
        }
    }

    @Override
    public void deleteTargetStandard(Integer id) {
        int i = targetStandardMapper.deleteByPrimaryKey(id);
        if(i != 1){
            throw new ItemException(ExceptionEnum.TARGET_DELETE_ERROR);
        }
    }
}
