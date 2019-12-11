package com.xinlang.cat_project.item.service.impl;

import com.xinlang.cat_project.item.enums.ExceptionEnum;
import com.xinlang.cat_project.item.exception.ItemException;
import com.xinlang.cat_project.item.fegin.ConsumeProjectUser;
import com.xinlang.cat_project.item.mapper.ItemContentMapper;
import com.xinlang.cat_project.item.mapper.ItemTargetMapper;
import com.xinlang.cat_project.item.mapper.ItemUserMapper;
import com.xinlang.cat_project.item.pojo.ItemTarget;
import com.xinlang.cat_project.item.service.IItemTargetService;
import com.xinlang.zly_xyx.cat_common.service.impl.BaseService;
import com.xinlang.zly_xyx.cat_common.utils.AppUserUtil;
import com.xinlang.zly_xyx.cat_common.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ItemTargetService extends BaseService<ItemTarget> implements IItemTargetService {

    @Autowired
    private ItemTargetMapper itemTargetMapper;

    @Autowired
    private ItemContentMapper itemContentMapper;

    @Autowired
    private ItemUserMapper itemUserMapper;

    @Autowired
    private ConsumeProjectUser consumeProjectUser;


    @Override
    public void saveTargets(List<ItemTarget> itemTargets) {
        int count = itemTargetMapper.insertList(itemTargets);
        if (count < 1){
            throw new ItemException(ExceptionEnum.SAVE_ERROR);
        }
    }
}
