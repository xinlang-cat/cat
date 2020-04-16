package com.xinlang.cat_project.item.controller;

import com.xinlang.cat_project.item.VO.ItemInformationViceVO;
import com.xinlang.cat_project.item.pojo.*;
import com.xinlang.cat_project.item.service.*;
import com.xinlang.zly_xyx.cat_common.utils.AppUserUtil;
import com.xinlang.zly_xyx.log.LogAnnotation;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author 梁应昌
 * 2019/9/27
 */

@RestController
@RequestMapping("/item/basic")
public class ItemBasicController {

    @Autowired
    private IItemBasicService itemBasicService;
    @Autowired
    private IModifyApplyService modifyApplyService;

    @Autowired
    private IItemInformationViceService itemInformationViceService;
    @Autowired
    private IItemIndicatorsViceService itemIndicatorsViceService;
    @Autowired
    private IItemSchedulingViceService itemSchedulingViceService;
    @Autowired
    private IItemPersonnelViceService itemPersonnelViceService;
    @Autowired
    private IItemFundBudgetViceService itemFundBudgetViceService;
    @Autowired
    private IItemFundSourceViceService itemFundSourceViceService;
    @Autowired
    private IItemContactWayViceService itemContactWayViceService;

    /**
     * 查询项目，分页查询
     *
     * @param page   当前页
     * @param rows   每页大小
     * @param sortBy 排序字段 (预留，暂时没用到)
     * @param desc   是否为降序 (预留，暂时没用到)
     * @param params 参数
     * @return
     */
    @LogAnnotation(module = "获取项目列表")
    @GetMapping("/page")
    public ResponseEntity<PageResult<ItemBasic>> getItemAll(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                                            @RequestParam(value = "rows", defaultValue = "10") Integer rows,
                                                            @RequestParam(value = "sortBy", required = false) String sortBy,
                                                            @RequestParam(value = "desc", defaultValue = "false") Boolean desc,
                                                            @RequestParam(required = false) Map<String, Object> params) {

        PageResult<ItemBasic> result = itemBasicService.queryList(page, rows, sortBy, desc, params);
        return ResponseEntity.ok(result);
    }

    @ApiOperation(value = "添加项目信息")
    @LogAnnotation(module = "添加项目信息")
    @PreAuthorize("hasAnyAuthority('project:item:save')")
    @PostMapping
    public ResponseEntity<ItemBasic> saveItem(@RequestBody ItemBasic itemBasic) {
        //获取当前用户ID,并SET编辑人
        Integer userId = AppUserUtil.getLoginAppUser().getId().intValue();
        itemBasic.setEdit_userid(userId);
        itemBasic.setEdit_date(new Date());
        itemBasicService.save(itemBasic);
        return ResponseEntity.status(HttpStatus.CREATED).body(itemBasic);
    }

    @ApiOperation(value = "查询项目信息")
    @LogAnnotation(module = "查询项目信息")
    @GetMapping("list")
    public ResponseEntity<List<ItemBasic>> getItemById(@RequestParam Map<String, Object> params) {
        List<ItemBasic> basic = itemBasicService.findListByParams(params, ItemBasic.class);
        return ResponseEntity.ok(basic);
    }

    @ApiOperation(value = "查询当前用户相关的项目")
    @LogAnnotation(module = "查询当前用户相关的项目")
    @GetMapping("/company")
    public ResponseEntity<List<ItemBasic>> getCompanyItem() {
        List<ItemBasic> basic = itemBasicService.queryCompanyItem();
        return ResponseEntity.ok(basic);
    }

    @ApiOperation(value = "修改项目信息")
    @LogAnnotation(module = "修改项目信息")
    @PreAuthorize("hasAnyAuthority('project:item:update')")
    @PutMapping
    public ResponseEntity<Void> updateItem(@RequestBody ItemBasic itemBasic) {
        //获取当前用户ID,并SET编辑人
        Integer userId = AppUserUtil.getLoginAppUser().getId().intValue();
        itemBasic.setEdit_userid(userId);
        itemBasic.setEdit_date(new Date());
        itemBasicService.update(itemBasic);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "删除项目信息")
    @LogAnnotation(module = "删除项目信息")
    @PreAuthorize("hasAnyAuthority('project:item:delete')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Integer id) {
        itemBasicService.delete(id);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "废弃项目")
    @LogAnnotation(module = "废弃项目")
    @PreAuthorize("hasAnyAuthority('project:item:discard')")
    @PutMapping("/discard/{id}")
    public ResponseEntity<Void> discardItem(@PathVariable Integer id) {
        itemBasicService.discardItem(id);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @ApiOperation(value = "查询变更申请")
    @LogAnnotation(module = "查询变更申请")
    @GetMapping("/modifyApply/list")
    public ResponseEntity<List<modifyApply>> getApplyById(@RequestParam Map<String, Object> params) {
        List<modifyApply> targets = modifyApplyService.findListByParams(params, modifyApply.class);

        return ResponseEntity.ok(targets);
    }

    @ApiOperation(value = "修改变更申请")
    @LogAnnotation(module = "修改变更申请")
    @PutMapping("/modifyApply")
    public ResponseEntity<Void> updateApply(@RequestBody modifyApply modifyApply) {
        modifyApplyService.update(modifyApply);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "查询变更申请")
    @LogAnnotation(module = "查询变更申请")
    @GetMapping("/modifyApply/lists")
    public ResponseEntity<List<modifyApply>> getApplyCheck(@RequestParam Map<String, Object> params) {
        List<modifyApply> targets = modifyApplyService.findApplyList(params, modifyApply.class);
        return ResponseEntity.ok(targets);
    }

    @LogAnnotation(module = "获取变更申请列表")
    @GetMapping("/modifyApply/page")
    public ResponseEntity<PageResult<modifyApply>> getModifyApplyAll(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                                                     @RequestParam(value = "rows", defaultValue = "10") Integer rows,
                                                                     @RequestParam(value = "sortBy", required = false) String sortBy,
                                                                     @RequestParam(value = "desc", defaultValue = "false") Boolean desc,
                                                                     @RequestParam(required = false) Map<String, Object> params) {


        PageResult<modifyApply> result = modifyApplyService.queryList(page, rows, sortBy, desc, params);
        return ResponseEntity.ok(result);
    }

    @ApiOperation(value = "添加变更申请(基本信息)")
    @LogAnnotation(module = "添加变更申请(基本信息)")
    @PostMapping("/modifyApply/base")
    public ResponseEntity<ItemInformationViceVO> ModifyBase(@RequestBody ItemInformationViceVO itemInformationViceVO) throws ParseException {
        //获取基本信息
        ItemInformationVice information = itemInformationViceVO.getInformation();
        //获取更改信息
        modifyApply modifyApply = itemInformationViceVO.getModifyApply();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");

        Integer userId = AppUserUtil.getLoginAppUser().getId().intValue();
        Integer type = modifyApply.getType();
        Integer apply_id = modifyApply.getId();
        //将修改信息存入数据库，获取申请id
        modifyApply.setUser_id(userId);
        modifyApply.setApply_time(new Date());

        modifyApplyService.save(modifyApply);
        System.err.println(modifyApply);
        apply_id = modifyApply.getId();

        information.setApply_id(apply_id);
        information.setEdit_user_id(userId);
        information.setEdit_date(new Date());
        if (null!=information.getPeriod()&&!information.getPeriod().isEmpty() && !information.getPeriod().equals("")){
            Date date = null;
            date = simpleDateFormat.parse(information.getPeriod().substring(0, 7));
            information.setStart_date(date);
            date = simpleDateFormat.parse(information.getPeriod().substring(10));
            information.setEnd_date(date);
        }
        itemInformationViceService.save(information);

        return ResponseEntity.status(HttpStatus.CREATED).body(itemInformationViceVO);
    }



    @ApiOperation(value = "添加变更申请(单位/负责人)")
    @LogAnnotation(module = "添加变更申请(单位/负责人)")
    @PostMapping("/modifyApply/linkMan")
    public ResponseEntity<ItemInformationViceVO> ModifyLinkMan(@RequestBody ItemInformationViceVO itemInformationViceVO) throws ParseException {
        //获取基本信息
        ItemInformationVice information = itemInformationViceVO.getInformation();
        //获取更改信息
        modifyApply modifyApply = itemInformationViceVO.getModifyApply();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");

        Integer userId = AppUserUtil.getLoginAppUser().getId().intValue();
        Integer type = modifyApply.getType();
        Integer apply_id = modifyApply.getId();
        //将修改信息存入数据库，获取申请id
        modifyApply.setUser_id(userId);
        modifyApply.setApply_time(new Date());

        modifyApplyService.save(modifyApply);
        System.err.println(modifyApply);
        apply_id = modifyApply.getId();

        information.setApply_id(apply_id);
        information.setEdit_user_id(userId);
        information.setEdit_date(new Date());
        if (null!=information.getPeriod()&&!information.getPeriod().isEmpty() && !information.getPeriod().equals("")){
            Date date = null;
            date = simpleDateFormat.parse(information.getPeriod().substring(0, 7));
            information.setStart_date(date);
            date = simpleDateFormat.parse(information.getPeriod().substring(10));
            information.setEnd_date(date);
        }
        itemInformationViceService.save(information);

        List<ItemContactWayVice> contactWays = itemInformationViceVO.getContactWays();
        for (ItemContactWayVice contactWay : contactWays) {
            contactWay.setItem_id(apply_id);
        }
        itemContactWayViceService.saveContactWays(contactWays);
        return ResponseEntity.status(HttpStatus.CREATED).body(itemInformationViceVO);
    }

    @ApiOperation(value = "添加变更申请(经费/预算)")
    @LogAnnotation(module = "添加变更申请(经费/预算)")
    @PostMapping("/modifyApply/found")
    public ResponseEntity<ItemInformationViceVO> ModifyFound(@RequestBody ItemInformationViceVO itemInformationViceVO) throws ParseException {
        //获取基本信息

        //获取更改信息
        modifyApply modifyApply = itemInformationViceVO.getModifyApply();

        Integer userId = AppUserUtil.getLoginAppUser().getId().intValue();

        //将修改信息存入数据库，获取申请id
        modifyApply.setUser_id(userId);
        modifyApply.setApply_time(new Date());
        modifyApplyService.save(modifyApply);
        System.err.println(modifyApply);
        Integer apply_id = modifyApply.getId();

        List<ItemFundBudgetVice> fundBudgets = itemInformationViceVO.getFundBudgets();
        int total = 0;
        for (ItemFundBudgetVice fundBudget : fundBudgets) {
            if (fundBudget.getMoney() != null) {
                total += fundBudget.getMoney();
            }
            fundBudget.setItem_id(apply_id);
        }
        itemFundBudgetViceService.saveFundBudgets(fundBudgets);
        //资金来源
        ItemFundSourceVice fundSource = itemInformationViceVO.getFundSource();
        fundSource.setItem_id(apply_id);
        fundSource.setFirst_party_provide(total);
        itemFundSourceViceService.save(fundSource);


        return ResponseEntity.status(HttpStatus.CREATED).body(itemInformationViceVO);
    }

    @ApiOperation(value = "添加变更申请(人员/指标)")
    @LogAnnotation(module = "添加变更申请(人员/指标)")
    @PostMapping("/modifyApply/target")
    public ResponseEntity<ItemInformationViceVO> ModifyTarget(@RequestBody ItemInformationViceVO itemInformationViceVO) throws ParseException {
        //获取基本信息

        //获取更改信息
        modifyApply modifyApply = itemInformationViceVO.getModifyApply();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
        Integer userId = AppUserUtil.getLoginAppUser().getId().intValue();

        //将修改信息存入数据库，获取申请id
        modifyApply.setUser_id(userId);
        modifyApply.setApply_time(new Date());
        modifyApplyService.save(modifyApply);
        System.err.println(modifyApply);
        Integer apply_id = modifyApply.getId();

        //指标
        List<ItemIndicatorsVice> indicators = itemInformationViceVO.getIndicators();
        for (ItemIndicatorsVice indicator : indicators) {
            indicator.setItem_id(apply_id);
            Date date = null;
            date = simpleDateFormat.parse(indicator.getPeriod().substring(0, 7));
            indicator.setStart_date(date);
            date = simpleDateFormat.parse(indicator.getPeriod().substring(10));
            indicator.setEnd_date(date);
        }
        itemIndicatorsViceService.saveIndicators(indicators);
        List<ItemIndicatorsVice> achievements = itemInformationViceVO.getAchievements();
        for (ItemIndicatorsVice achievement : achievements) {
            achievement.setItem_id(apply_id);
        }
        itemIndicatorsViceService.saveIndicators(achievements);


        //人员
        List<ItemPersonnelVice> personnels = itemInformationViceVO.getPersonnels();
        for (ItemPersonnelVice personnel : personnels) {
            personnel.setItem_id(apply_id);
        }
        itemPersonnelViceService.savePersonnels(personnels);


        return ResponseEntity.status(HttpStatus.CREATED).body(itemInformationViceVO);
    }
}
