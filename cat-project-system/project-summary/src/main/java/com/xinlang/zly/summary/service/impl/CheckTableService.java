package com.xinlang.zly.summary.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xinlang.cat_project.item.mapper.AuditApplyMapper;
import com.xinlang.cat_project.item.pojo.PageResult;
import com.xinlang.cat_project.item.pojo.auditApply;
import com.xinlang.zly.summary.bean.CheckTable;
import com.xinlang.zly.summary.mapper.CheckTableMapper;
import com.xinlang.zly.summary.service.ICheckTableService;
import com.xinlang.zly_xyx.cat_common.service.impl.BaseService;
import com.xinlang.zly_xyx.common.Page;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019-12-17
 */
@Service
@Transactional
public class CheckTableService extends BaseService<CheckTable> implements ICheckTableService {
    @Autowired
    private CheckTableMapper checkTableMapper;

    @Override
    public PageResult<CheckTable> queryList(Integer page, Integer rows, String sortBy, Boolean desc, Map<String, Object> params) {
        // 开始分页
        PageHelper.startPage(page, rows);
        // 过滤
        Example example = new Example(CheckTable.class);

        if ( params.get("status") != "") {
            if ( params.get("entrusting_company") != "" ) {

                //甲方
                example.createCriteria().andEqualTo("status", params.get("status")).andEqualTo("entrusting_company", params.get("entrusting_company"));
            } else if (params.get("management_company") != "" ) {

                //监理
                example.createCriteria().andEqualTo("status", params.get("status")).andEqualTo("management_company", params.get("management_company"));
            } else if (params.get("acceptance_company") != "" ) {

                //验收
                example.createCriteria().andEqualTo("status", params.get("status")).andEqualTo("acceptance_company", params.get("acceptance_company"));
            } if ( params.get("entrusting_company") != "" ) {

                //甲方
                example.createCriteria().andEqualTo("status", params.get("status")).andEqualTo("entrusting_company", params.get("entrusting_company"));
            } else if (params.get("management_company") != "" ) {

                //监理
                example.createCriteria().andEqualTo("status", params.get("status")).andEqualTo("management_company", params.get("management_company"));
            } else if (params.get("acceptance_company") != "" ) {

                //验收
                example.createCriteria().andEqualTo("status", params.get("status")).andEqualTo("acceptance_company", params.get("acceptance_company"));
            }
            //状态
            example.createCriteria().andEqualTo("status", params.get("status"));
        } else if ( params.get("entrusting_company") != "" ) {

            //甲方
            example.createCriteria().andNotEqualTo("status", 0).andEqualTo("entrusting_company", params.get("entrusting_company"));
        } else if (params.get("management_company") != "" ) {

            //监理
            example.createCriteria().andNotEqualTo("status", 0).andEqualTo("management_company", params.get("management_company"));
        } else if (params.get("acceptance_company") != "" ) {

            //验收
            example.createCriteria().andNotEqualTo("status", 0).andEqualTo("acceptance_company", params.get("acceptance_company"));
        }


        if (StringUtils.isNotBlank(sortBy)) {
            // 排序
            String orderByClause = sortBy + (desc ? " DESC" : " ASC");
            example.setOrderByClause(orderByClause);
        }
        //查询
        List<CheckTable> list = checkTableMapper.selectByExample(example);

        //解析分页结果
        PageInfo<CheckTable> info = new PageInfo<>(list);

        return new PageResult<>(200, info.getTotal(), list);
    }
}
