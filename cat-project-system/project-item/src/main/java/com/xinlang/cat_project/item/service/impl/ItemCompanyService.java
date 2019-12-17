package com.xinlang.cat_project.item.service.impl;

import com.xinlang.cat_project.item.enums.ExceptionEnum;
import com.xinlang.cat_project.item.exception.ItemException;
import com.xinlang.cat_project.item.mapper.ItemCompanyMapper;
import com.xinlang.cat_project.item.pojo.ItemCompany;
import com.xinlang.cat_project.item.service.IItemCompanyService;
import com.xinlang.zly_xyx.cat_common.service.impl.BaseService;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemCompanyService extends BaseService<ItemCompany> implements IItemCompanyService {

    @Autowired
    private ItemCompanyMapper itemCompanyMapper;

    @Override
    public void saveCompanys(List<ItemCompany> itemCompanys) {
        //批量插入
        int count = itemCompanyMapper.insertList(itemCompanys);
        if (count < 1){
            throw new ItemException(ExceptionEnum.SAVE_ERROR);
        }
    }
}
