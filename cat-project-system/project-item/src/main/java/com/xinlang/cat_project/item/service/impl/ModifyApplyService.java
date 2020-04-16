package com.xinlang.cat_project.item.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xinlang.cat_project.item.mapper.ModifyApplyMapper;
import com.xinlang.cat_project.item.pojo.PageResult;
import com.xinlang.cat_project.item.pojo.modifyApply;
import com.xinlang.cat_project.item.service.IModifyApplyService;
import com.xinlang.zly_xyx.cat_common.service.impl.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
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
    public List<modifyApply> findApplyList(Map<String, Object> params, Class<modifyApply> modifyApplyClass) {
        Example example = new Example(modifyApply.class);
        if (params.get("status") != "" && params.get("item_id") != ""){
            example.createCriteria().andEqualTo("status",params.get("status")).andEqualTo("item_id",params.get("item_id"));
        }else {
            example.createCriteria().andNotEqualTo("status",-1).andEqualTo("item_id",params.get("item_id"));
        }
        List<modifyApply> list =modifyApplyMapper.selectByExample(example);
        return list;
    }

    @Override
    public PageResult<modifyApply> queryList(Integer page, Integer rows, String sortBy, Boolean desc, Map<String, Object> params) {
        // 开始分页
        PageHelper.startPage(page, rows);
        // 过滤
        Example example = new Example(modifyApply.class);

        if (params.get("status") != "" && params.get("manage_unit") != "" && params.get("check_unit") != ""){
            example.createCriteria().andEqualTo("status",params.get("status")).andEqualTo("manage_unit", params.get("manage_unit")).andEqualTo("check_unit", params.get("check_unit"));
        }else if (params.get("check_unit") != "" && params.get("manage_unit") != ""){
            example.createCriteria().andNotEqualTo("status",0).andEqualTo("check_unit", params.get("check_unit")).andEqualTo("manage_unit", params.get("manage_unit"));
        }else if (params.get("status") != "" && params.get("manage_unit") != "") {
            example.createCriteria().andEqualTo("status", params.get("status")).andEqualTo("manage_unit", params.get("manage_unit"));
        }else if (params.get("status") != "" && params.get("check_unit") !=  "" ){
            example.createCriteria().andEqualTo("status",params.get("status")).andEqualTo("check_unit", params.get("check_unit"));
        }else if (params.get("check_unit") != ""){
            example.createCriteria().andNotEqualTo("status",0).andEqualTo("check_unit", params.get("check_unit"));
        }else if (params.get("manage_unit") != ""){
            example.createCriteria().andNotEqualTo("status",0).andEqualTo("manage_unit", params.get("manage_unit"));
        }else if (params.get("status") != ""){
            example.createCriteria().andNotEqualTo("status",params.get("status"));
        }else {
            example.createCriteria().andNotEqualTo("status",0);
        }
        if (StringUtils.isNotBlank(sortBy)) {
            // 排序
            String orderByClause = sortBy + (desc ? " DESC" : " ASC");
            example.setOrderByClause(orderByClause);
        }
        //查询
        List<modifyApply> list =modifyApplyMapper.selectByExample(example);
        //解析分页结果
        PageInfo<modifyApply> info = new PageInfo<>(list);
        return  new PageResult<>(200, info.getTotal(), list);
    }
}
