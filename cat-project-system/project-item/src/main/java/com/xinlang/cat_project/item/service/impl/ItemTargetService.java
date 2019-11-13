package com.xinlang.cat_project.item.service.impl;

import com.xinlang.bean.project_user.ProjectUser;
import com.xinlang.cat_project.item.enums.ExceptionEnum;
import com.xinlang.cat_project.item.exception.ItemException;
import com.xinlang.cat_project.item.fegin.ConsumeProjectUser;
import com.xinlang.cat_project.item.mapper.ItemContentMapper;
import com.xinlang.cat_project.item.mapper.ItemTargetMapper;
import com.xinlang.cat_project.item.mapper.ItemUserMapper;
import com.xinlang.cat_project.item.pojo.ItemContent;
import com.xinlang.cat_project.item.pojo.ItemTarget;;
import com.xinlang.cat_project.item.pojo.ItemUser;
import com.xinlang.cat_project.item.service.IItemTargetService;
import com.xinlang.zly_xyx.cat_common.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class ItemTargetService implements IItemTargetService {

    @Autowired
    private ItemTargetMapper itemTargetMapper;

    @Autowired
    private ItemContentMapper itemContentMapper;

    @Autowired
    private ItemUserMapper itemUserMapper;

    @Autowired
    private ConsumeProjectUser consumeProjectUser;

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
    public List<Map<String, Object>> queryTargetByCId(Integer Cid) {
        //存放结果数据
        List<Map<String, Object>> targetInfos = new ArrayList<>();

        //查找所有指标
        ItemTarget target = new ItemTarget();
        target.setContent_id(Cid);
        List<ItemTarget> list = itemTargetMapper.select(target);
        //循环查找所有相关成员
        ItemUser itemUser = new ItemUser(); //关系数据
        for (ItemTarget t : list) {
            Map<String, Object> itemTarget = new HashMap<>();
            t.setStart_dateStr(DateUtils.dateToString(t.getStart_date(), "yyyy年MM月dd日"));
            t.setEnd_dateStr(DateUtils.dateToString(t.getEnd_date(), "yyyy年MM月dd日"));
            //先存指标
            itemTarget.put("itemTarget",t);

            //通过target_id查找相关成员
            itemUser.setTarget_id(t.getId());
            List<ItemUser> itemUsers = itemUserMapper.select(itemUser);
            //循环查找成员信息
            List<ProjectUser> projectUser;
            List<ProjectUser> PU = new ArrayList<>();
            for (ItemUser u : itemUsers) {
                projectUser = consumeProjectUser.findByUserId(u.getUser_id());
                PU.add(projectUser.get(0));
            }
            //存成员信息
            itemTarget.put("projectUser",PU);

            targetInfos.add(itemTarget); //添加进list
        }
        return targetInfos;
    }

    @Override
    public List<Map<String, Object>> queryTargetByItemId(Integer itemId) {
        //存放结果数据
        List<Map<String, Object>> targetInfos = new ArrayList<>();

        //查找所有指标
        ItemTarget target = new ItemTarget();
        target.setItem_id(itemId);
        List<ItemTarget> list = itemTargetMapper.select(target);
        //循环查找所有相关成员
        ItemUser itemUser = new ItemUser(); //关系数据
        for (ItemTarget t : list) {
            Map<String, Object> itemTarget = new HashMap<>();
            t.setStart_dateStr(DateUtils.dateToString(t.getStart_date(), "yyyy年MM月dd日"));
            t.setEnd_dateStr(DateUtils.dateToString(t.getEnd_date(), "yyyy年MM月dd日"));
            //先存指标
            itemTarget.put("itemTarget",t);

            //通过target_id查找相关成员
            itemUser.setTarget_id(t.getId());
            List<ItemUser> itemUsers = itemUserMapper.select(itemUser);
            //循环查找成员信息
            List<ProjectUser> projectUser;
            List<ProjectUser> PU = new ArrayList<>();
            for (ItemUser u : itemUsers) {
                projectUser = consumeProjectUser.findByUserId(u.getUser_id());
                PU.add(projectUser.get(0));
            }
            //存成员信息
            itemTarget.put("projectUser",PU);

            targetInfos.add(itemTarget); //添加进list
        }
        return targetInfos;
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
       /* //修改实施人员
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
        }*/
    }

    @Override
    @Transactional
    public void deleteTarget(Integer id) {
        int i = itemTargetMapper.deleteByPrimaryKey(id);
        if(i != 1){
            throw new ItemException(ExceptionEnum.TARGET_UPDATE_ERROR);
        }
        //删除成员与指标的关系
        itemTargetMapper.updateItemUser2(id);
    }

}
