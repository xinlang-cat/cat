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



    @ApiOperation(value = "添加更改申请")
    @LogAnnotation(module = "添加更改申请")
    @PostMapping("/modifyApply")
    public ResponseEntity<ItemInformationViceVO> ModifyApply(@RequestBody ItemInformationViceVO itemInformationViceVO) throws ParseException {
        ItemInformationVice information = itemInformationViceVO.getInformation();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
        Integer userId = AppUserUtil.getLoginAppUser().getId().intValue();
        System.err.println(information);
        Integer apply_id;
        if (information.getApply_id() != null) {
            //更改申请已存在
            apply_id = information.getApply_id();

            modifyApply modifyApply = new modifyApply();
            modifyApply.setId(information.getApply_id());
            modifyApply.setStatus(information.getStatus());
            modifyApplyService.update(modifyApply);
        } else {
            modifyApply modifyApply = new modifyApply();
            modifyApply.setUser_id(userId);
            modifyApply.setApply_time(new Date());
            modifyApply.setStatus(information.getStatus());
            modifyApply.setItem_id(information.getId());
            modifyApply.setManage_unit(information.getEntrusting_party());
            modifyApply.setCheck_unit(information.getManagement_unit());
            modifyApplyService.save(modifyApply);
            System.err.println(modifyApply);
            apply_id = modifyApply.getId();
        }


        if (information.getId() != null) {

            itemInformationViceService.delete(information.getId());


            ItemIndicatorsVice indicator = new ItemIndicatorsVice();
            indicator.setItem_id(information.getId());
            itemIndicatorsViceService.deleteByAttribute(indicator);

            ItemSchedulingVice scheduling = new ItemSchedulingVice();
            scheduling.setItem_id(information.getId());
            itemSchedulingViceService.deleteByAttribute(scheduling);

            ItemPersonnelVice personnel = new ItemPersonnelVice();
            personnel.setItem_id(information.getId());
            itemPersonnelViceService.deleteByAttribute(personnel);

            ItemFundBudgetVice fundBudget = new ItemFundBudgetVice();
            fundBudget.setItem_id(information.getId());
            itemFundBudgetViceService.deleteByAttribute(fundBudget);

            ItemFundSourceVice fundSource = new ItemFundSourceVice();
            fundSource.setItem_id(information.getId());
            itemFundSourceViceService.deleteByAttribute(fundSource);

            ItemContactWayVice contactWay = new ItemContactWayVice();
            contactWay.setItem_id(information.getId());
            itemContactWayViceService.deleteByAttribute(contactWay);

            information.setId(null);
        }
        information.setEdit_user_id(userId);
        information.setEdit_date(new Date());
        if (!information.getPeriod().equals("")) {
            Date date = null;
            date = simpleDateFormat.parse(information.getPeriod().substring(0, 7));
            information.setStart_date(date);
            date = simpleDateFormat.parse(information.getPeriod().substring(10));
            information.setEnd_date(date);
        }
        information.setApply_id(apply_id);
        itemInformationViceService.save(information);
        //指标
        List<ItemIndicatorsVice> indicators = itemInformationViceVO.getIndicators();
        for (ItemIndicatorsVice indicator : indicators) {
            indicator.setItem_id(information.getId());
            Date date = null;
            date = simpleDateFormat.parse(indicator.getPeriod().substring(0, 7));
            indicator.setStart_date(date);
            date = simpleDateFormat.parse(indicator.getPeriod().substring(10));
            indicator.setEnd_date(date);
        }
        itemIndicatorsViceService.saveIndicators(indicators);
        List<ItemIndicatorsVice> achievements = itemInformationViceVO.getAchievements();
        for (ItemIndicatorsVice achievement : achievements) {
            achievement.setItem_id(information.getId());
        }
        itemIndicatorsViceService.saveIndicators(achievements);
        //计划
        List<ItemSchedulingVice> schedulings = itemInformationViceVO.getScheduling();
        for (ItemSchedulingVice scheduling : schedulings) {
            scheduling.setItem_id(information.getId());
        }
        itemSchedulingViceService.saveSchedulings(schedulings);
        //人员
        List<ItemPersonnelVice> personnels = itemInformationViceVO.getPersonnels();
        for (ItemPersonnelVice personnel : personnels) {
            personnel.setItem_id(information.getId());
        }
        itemPersonnelViceService.savePersonnels(personnels);
        //资金预算
        List<ItemFundBudgetVice> fundBudgets = itemInformationViceVO.getFundBudgets();
        int total = 0;
        for (ItemFundBudgetVice fundBudget : fundBudgets) {
            if (fundBudget.getMoney() != null) {
                total += fundBudget.getMoney();
            }
            fundBudget.setItem_id(information.getId());
        }
        itemFundBudgetViceService.saveFundBudgets(fundBudgets);
        //资金来源
        ItemFundSourceVice fundSource = itemInformationViceVO.getFundSource();
        fundSource.setItem_id(information.getId());
        fundSource.setFirst_party_provide(total);
        itemFundSourceViceService.save(fundSource);
        //联系方式
        List<ItemContactWayVice> contactWays = itemInformationViceVO.getContactWays();
        for (ItemContactWayVice contactWay : contactWays) {
            contactWay.setItem_id(information.getId());
        }
        itemContactWayViceService.saveContactWays(contactWays);
        return ResponseEntity.status(HttpStatus.CREATED).body(itemInformationViceVO);
    }

    @ApiOperation(value = "查询更改申请")
    @LogAnnotation(module = "查询更改申请")
    @GetMapping("/modifyApply/list")
    public ResponseEntity<List<modifyApply>> getApplyById(@RequestParam Map<String, Object> params) {
        List<modifyApply> targets = modifyApplyService.findListByParams(params, modifyApply.class);

        return ResponseEntity.ok(targets);
    }

    @ApiOperation(value = "修改更改申请")
    @LogAnnotation(module = "修改更改申请")
    @PutMapping("/modifyApply")
    public ResponseEntity<Void> updateApply(@RequestBody modifyApply modifyApply) {
        modifyApplyService.update(modifyApply);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "查询更改申请")
    @LogAnnotation(module = "查询更改申请")
    @GetMapping("/modifyApply/lists")
    public ResponseEntity<List<modifyApply>> getApplyCheck(@RequestParam Map<String, Object> params) {
        List<modifyApply> targets = modifyApplyService.findApplyList(params, modifyApply.class);
        return ResponseEntity.ok(targets);
    }

    @LogAnnotation(module = "获取更改申请列表")
    @GetMapping("/modifyApply/page")
    public ResponseEntity<PageResult<modifyApply>> getModifyApplyAll(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                                                     @RequestParam(value = "rows", defaultValue = "10") Integer rows,
                                                                     @RequestParam(value = "sortBy", required = false) String sortBy,
                                                                     @RequestParam(value = "desc", defaultValue = "false") Boolean desc,
                                                                     @RequestParam(required = false) Map<String, Object> params) {


        PageResult<modifyApply> result = modifyApplyService.queryList(page, rows, sortBy, desc, params);
        return ResponseEntity.ok(result);
    }


}
