package com.xinlang.cat_project.item.service.impl;

import com.xinlang.cat_project.item.mapper.ItemInformationMapper;
import com.xinlang.cat_project.item.pojo.ItemInformation;
import com.xinlang.cat_project.item.service.IItemInformationService;
import com.xinlang.zly_xyx.cat_common.service.impl.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ItemInformationService extends BaseService<ItemInformation> implements IItemInformationService {

    @Autowired
    private ItemInformationMapper itemInformationMapper;
}
