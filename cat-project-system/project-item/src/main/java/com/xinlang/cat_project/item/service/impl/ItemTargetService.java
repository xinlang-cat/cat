package com.xinlang.cat_project.item.service.impl;

import com.xinlang.cat_project.item.VO.TargetInfo;
import com.xinlang.cat_project.item.VO.TargetInfoAll;
import com.xinlang.cat_project.item.enums.ExceptionEnum;
import com.xinlang.cat_project.item.exception.ItemException;
import com.xinlang.cat_project.item.mapper.ItemContentMapper;
import com.xinlang.cat_project.item.mapper.ItemTargetMapper;
import com.xinlang.cat_project.item.mapper.ItemUserMapper;
import com.xinlang.cat_project.item.pojo.ItemContent;
import com.xinlang.cat_project.item.pojo.ItemTarget;;
import com.xinlang.cat_project.item.service.IItemTargetService;
import com.xinlang.zly_xyx.cat_common.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
@Slf4j
public class ItemTargetService implements IItemTargetService {

    @Autowired
    private ItemTargetMapper itemTargetMapper;

    @Autowired
    private ItemContentMapper itemContentMapper;

    @Override
    @Transactional
    public void saveTarget(ItemTarget target) {
        try {
            target.setStart_date(DateUtils.stringToDate(target.getStart_dateStr(), "yyyy年MM月dd日"));
            target.setEnd_date(DateUtils.stringToDate(target.getEnd_dateStr(), "yyyy年MM月dd日"));
        }catch (Exception e){
            log.error("日期格式错误！",e);
            throw new ItemException(ExceptionEnum.DATE_FORMAT_ERROR);
        }
        int i = itemTargetMapper.insertSelective(target);
        if(i != 1){
            throw new ItemException(ExceptionEnum.TARGET_SAVE_ERROR);
        }
        //添加实施人员
        List<Integer> userIds = target.getUserIds();
        if(!CollectionUtils.isEmpty(userIds)){
            //通过content_id查询研发内容，从而获得项目id
            ItemContent itemContent = new ItemContent();
            itemContent.setId(target.getContent_id());
            ItemContent content = itemContentMapper.selectByPrimaryKey(itemContent);
            //循环添加成员
            for (Integer userId : userIds) {
                itemTargetMapper.insertItemUser(content.getItem_id(),target.getId(),userId);
                if(i != 1){
                    throw new ItemException(ExceptionEnum.TARGET_UPDATE_ERROR);
                }
            }
        }
    }

    @Override
    public ItemTarget queryTargetById(Integer id) {
        ItemTarget target = itemTargetMapper.selectByPrimaryKey(id);
        if(target==null){
            throw new ItemException(ExceptionEnum.DATA_NOT_FOUND);
        }
        target.setStart_dateStr(DateUtils.dateToString(target.getStart_date(), "yyyy年MM月dd日"));
        target.setEnd_dateStr(DateUtils.dateToString(target.getEnd_date(), "yyyy年MM月dd日"));
        return target;
    }

    @Override
    public TargetInfo queryTargetInfoById(Integer id) {
        TargetInfo targetInfo = itemTargetMapper.selectTargetInfoById(id);
        return targetInfo;
    }

    @Override
    public List<ItemTarget> queryTargetByCId(Integer Cid) {
        ItemTarget target = new ItemTarget();
        target.setContent_id(Cid);
        List<ItemTarget> list = itemTargetMapper.select(target);
        if(CollectionUtils.isEmpty(list)){
            throw new ItemException(ExceptionEnum.DATA_NOT_FOUND);
        }
        for (ItemTarget t : list) {
            t.setStart_dateStr(DateUtils.dateToString(t.getStart_date(), "yyyy年MM月dd日"));
            t.setEnd_dateStr(DateUtils.dateToString(t.getEnd_date(), "yyyy年MM月dd日"));
        }
        return list;
    }

    @Override
    public List<TargetInfoAll> queryTargetInfoAllByCId(Integer Cid) {
        List<TargetInfoAll> targetInfoAll = itemTargetMapper.selectTargetInfoAllById(Cid);
        return targetInfoAll;
    }

    @Override
    @Transactional
    public void updateTarget(ItemTarget target) {
        try {
            target.setStart_date(DateUtils.stringToDate(target.getStart_dateStr(), "yyyy年MM月dd日"));
            target.setEnd_date(DateUtils.stringToDate(target.getEnd_dateStr(), "yyyy年MM月dd日"));
        }catch (Exception e){
            log.error("日期格式错误！",e);
            throw new ItemException(ExceptionEnum.DATE_FORMAT_ERROR);
        }
        int i = itemTargetMapper.updateByPrimaryKeySelective(target);
        if(i != 1){
            throw new ItemException(ExceptionEnum.TARGET_UPDATE_ERROR);
        }
        //修改实施人员
        List<Integer> userIds = target.getUserIds();
        if(!CollectionUtils.isEmpty(userIds)){
            //查询实施人员id,先查询当前指标的详细信息
            TargetInfo targetInfo = this.queryTargetInfoById(target.getId());
            //获取实施人员信息

            //循环添加成员
            for (Integer userId : userIds) {
                int j = itemTargetMapper.updateItemUser(target.getId(),userId);
                if(j != 1){
                    throw new ItemException(ExceptionEnum.TARGET_UPDATE_ERROR);
                }
            }
        }
    }

    @Override
    @Transactional
    public void deleteTarget(Integer id) {
        int i = itemTargetMapper.deleteByPrimaryKey(id);
        if(i != 1){
            throw new ItemException(ExceptionEnum.TARGET_UPDATE_ERROR);
        }
        itemTargetMapper.updateItemUser2(id);
    }

}
