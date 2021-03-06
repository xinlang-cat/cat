package com.xinlang.cat_project.item.service;

import com.xinlang.bean.projectInfo.ItemInformation;
import com.xinlang.bean.util.PageResult;
import com.xinlang.zly_xyx.cat_common.service.IBaseService;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

public interface IItemInformationService extends IBaseService<ItemInformation> {
    PageResult<ItemInformation> queryList(Integer page, Integer rows, String sortBy, Boolean desc, Map<String, Object> params);

    List<ItemInformation> queryMyItem();

    List<ItemInformation> findListByYear(Map<String, Object> params, Class<ItemInformation> itemInformationClass) throws ParseException;

    PageResult<ItemInformation> queryListCom(Integer page, Integer rows, String sortBy, Boolean desc, Map<String, Object> params);
}
