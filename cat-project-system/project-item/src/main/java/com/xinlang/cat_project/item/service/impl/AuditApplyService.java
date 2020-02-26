package com.xinlang.cat_project.item.service.impl;

import com.xinlang.cat_project.item.mapper.AuditApplyMapper;
import com.xinlang.cat_project.item.pojo.auditApply;
import com.xinlang.cat_project.item.service.IAuditApplyService;
import com.xinlang.zly_xyx.cat_common.service.impl.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
