package com.xinlang.cat_project.item.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xinlang.cat_project.item.mapper.ItemInformationMapper;
import com.xinlang.cat_project.item.mapper.ItemPersonnelMapper;
import com.xinlang.bean.projectInfo.ItemInformation;
import com.xinlang.bean.projectInfo.ItemPersonnel;
import com.xinlang.bean.util.PageResult;
import com.xinlang.cat_project.item.service.IItemInformationService;
import com.xinlang.zly_xyx.cat_common.service.impl.BaseService;
import com.xinlang.zly_xyx.cat_common.utils.AppUserUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class ItemInformationService extends BaseService<ItemInformation> implements IItemInformationService {

    @Autowired
    private ItemInformationMapper itemInformationMapper;
    @Autowired
    private ItemPersonnelMapper itemPersonnelMapper;

    @Override
    public PageResult<ItemInformation> queryList(Integer page, Integer rows, String sortBy, Boolean desc, Map<String, Object> params) {
        // 开始分页
        PageHelper.startPage(page, rows);
        // 过滤
        Example example = new Example(ItemInformation.class);

        if (params.get("name") != "" && params.get("type") != "") {
            example.createCriteria().andLike("name", "%" + params.get("name") + "%")
                    .andEqualTo("type", params.get("type"))
                    .andEqualTo("edit_user_id", params.get("userId"))
                    .andBetween("status", params.get("status1"), params.get("status2"));
        } else if (params.get("name") != "") {
            example.createCriteria().andLike("name", "%" + params.get("name") + "%")
                    .andEqualTo("edit_user_id", params.get("userId"))
                    .andBetween("status", params.get("status1"), params.get("status2"));
        } else if (params.get("type") != "") {
            example.createCriteria().andEqualTo("type", params.get("type"))
                    .andEqualTo("edit_user_id", params.get("userId"))
                    .andBetween("status", params.get("status1"), params.get("status2"));
        } else {
            example.createCriteria().andEqualTo("edit_user_id", params.get("userId"))
                    .andBetween("status", params.get("status1"), params.get("status2"));
        }
        if (StringUtils.isNotBlank(sortBy)) {
            // 排序
            String orderByClause = sortBy + (desc ? " DESC" : " ASC");
            example.setOrderByClause(orderByClause);
        }
        //查询
        List<ItemInformation> list = itemInformationMapper.selectByExample(example);
        //解析分页结果
        PageInfo<ItemInformation> info = new PageInfo<>(list);
        return new PageResult<>(200, info.getTotal(), list);
    }

    @Override
    public List<ItemInformation> queryMyItem() {
        //用于保存项目信息
        List<ItemInformation> list = new ArrayList<>();
        //先获取当前登录用户ID
        Integer userId = AppUserUtil.getLoginAppUser().getId().intValue();
        //通过用户ID获取用户相关的项目ID
        ItemPersonnel personnel = new ItemPersonnel();
        personnel.setUser_id(userId);
        List<ItemPersonnel> personnels = itemPersonnelMapper.select(personnel);
        if (!CollectionUtils.isEmpty(personnels)) {
            //不为空，则说明在项目中属于承担方的成员
            //遍历项目ID查询项目
            for (ItemPersonnel p : personnels) {
                ItemInformation itemInformation = itemInformationMapper.selectByPrimaryKey(p.getItem_id());
                if (itemInformation != null&&itemInformation.getStatus()>1) {
                    list.add(itemInformation);
                }
            }
        }
        return list;
    }

    @Override
    public List<ItemInformation> findListByYear(Map<String, Object> params, Class<ItemInformation> itemInformationClass) {
        Example example = new Example(ItemInformation.class);
        example.createCriteria().andEqualTo( "YEAR(create_date)",params.get("year"));
        List<ItemInformation> list = itemInformationMapper.selectByExample(example);
        return list;
    }

    @Override
    public PageResult<ItemInformation> queryListCom(Integer page, Integer rows, String sortBy, Boolean desc, Map<String, Object> params) {
        // 开始分页
        PageHelper.startPage(page, rows);
        // 过滤
        Example example = new Example(ItemInformation.class);
        System.err.println("entrusting_party="+params.get("entrusting_party"));
        System.err.println("responsible_unit="+params.get("responsible_unit"));
        System.err.println("management_unit="+params.get("management_unit"));
        System.err.println("items="+params.get("items"));
        if (params.get("entrusting_party") != null ) {
            System.err.println("entrusting_party="+params.get("entrusting_party"));
            example.createCriteria().andEqualTo( "entrusting_party",params.get("entrusting_party"));
        } else if (params.get("responsible_unit") != null) {
            System.err.println("responsible_unit="+params.get("responsible_unit"));
            example.createCriteria().andEqualTo( "responsible_unit",params.get("responsible_unit"));
        } else if (params.get("management_unit") != null) {
            System.err.println("management_unit="+params.get("management_unit"));
            example.createCriteria().andEqualTo( "management_unit",params.get("management_unit"));
        } else if(params.get("items") != null){
            String items = (String) params.get("items");
            String[] itemIds = items.split(",");
            example.createCriteria().andIn( "id", Arrays.asList(itemIds));
        }else {
            example.createCriteria().andEqualTo( "id",0);
        }
        if (StringUtils.isNotBlank(sortBy)) {
            // 排序
            String orderByClause = sortBy + (desc ? " DESC" : " ASC");
            example.setOrderByClause(orderByClause);
        }
        //查询
        List<ItemInformation> list = itemInformationMapper.selectByExample(example);
        //解析分页结果
        PageInfo<ItemInformation> info = new PageInfo<>(list);
        return new PageResult<>(200, info.getTotal(), list);
    }
}
