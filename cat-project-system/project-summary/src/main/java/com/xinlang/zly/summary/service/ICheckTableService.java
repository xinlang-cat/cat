package com.xinlang.zly.summary.service;

import com.xinlang.bean.util.PageResult;
import com.xinlang.zly.summary.bean.CheckTable;
import com.xinlang.zly_xyx.cat_common.service.IBaseService;
import com.xinlang.zly_xyx.common.Page;

import java.util.Map;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019-12-17
 */
public interface ICheckTableService extends IBaseService<CheckTable> {
    PageResult<CheckTable> queryList(Integer page, Integer rows, String sortBy, Boolean desc, Map<String, Object> params);
}
