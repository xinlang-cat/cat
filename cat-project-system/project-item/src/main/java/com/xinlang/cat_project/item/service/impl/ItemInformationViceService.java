package com.xinlang.cat_project.item.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xinlang.cat_project.item.mapper.ItemInformationMapper;
import com.xinlang.cat_project.item.mapper.ItemPersonnelMapper;
import com.xinlang.cat_project.item.mapper.ItemPersonnelViceMapper;
import com.xinlang.cat_project.item.pojo.*;
import com.xinlang.cat_project.item.service.IItemInformationService;
import com.xinlang.cat_project.item.service.IItemInformationViceService;
import com.xinlang.zly_xyx.cat_common.service.impl.BaseService;
import com.xinlang.zly_xyx.cat_common.utils.AppUserUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class ItemInformationViceService extends BaseService<ItemInformationVice> implements IItemInformationViceService {

    @Autowired
    private ItemInformationViceService itemInformationMapper;


}
