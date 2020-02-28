package com.xinlang.cat_project.item.service.impl;

import com.xinlang.cat_project.item.mapper.AuditApplyMapper;
import com.xinlang.cat_project.item.pojo.auditApply;
import com.xinlang.cat_project.item.service.IAuditApplyService;
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
public class AuditApplyService extends BaseService<auditApply> implements IAuditApplyService {

    @Autowired
    private AuditApplyMapper auditApplyMapper;


    @Override
    public void add(auditApply auditApply) {
        auditApplyMapper.insert(auditApply);
    }

    @Override
    public List<auditApply> findApplyList(Map<String, Object> params, Class<auditApply> auditApplyClass) {
        Example example = new Example(auditApply.class);
       if (params.get("status") != ""){
            example.createCriteria().andEqualTo("status",params.get("status"));
        }else {
            example.createCriteria().andNotEqualTo("status",0);
        }
        List<auditApply> list =auditApplyMapper.selectByExample(example);
        return list;
    }
}
