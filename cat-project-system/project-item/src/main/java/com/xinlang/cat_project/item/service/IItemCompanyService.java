package com.xinlang.cat_project.item.service;

import com.xinlang.cat_project.item.pojo.ItemCompany;
import com.xinlang.zly_xyx.cat_common.service.IBaseService;

import java.util.List;

public interface IItemCompanyService extends IBaseService<ItemCompany> {
    void saveCompanys(List<ItemCompany> itemCompanys);
}
