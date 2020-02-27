package com.xinlang.cat_project.item.service.impl;


import com.xinlang.cat_project.item.mapper.ModifyApplyMapper;
import com.xinlang.cat_project.item.pojo.auditApply;
import com.xinlang.cat_project.item.pojo.modifyApply;
import com.xinlang.cat_project.item.service.IModifyApplyService;
import com.xinlang.zly_xyx.cat_common.service.impl.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
@Transactional
public class ModifyApplyService extends BaseService<modifyApply> implements IModifyApplyService {

    @Autowired
    private ModifyApplyMapper modifyApplyMapper;


    @Override
    public void add(modifyApply modifyApply) {

    }

    @Override
    public List<modifyApply> findApplyList(Map<String, Object> params, Class<modifyApply> modifyApplyClass) {
        Example example = new Example(modifyApply.class);
        if (params.get("status") != ""){
            example.createCriteria().andEqualTo("status",params.get("status"));
        }else {
            example.createCriteria().andNotEqualTo("status",-1);
        }
        List<modifyApply> list =modifyApplyMapper.selectByExample(example);
        return list;
    }
}
