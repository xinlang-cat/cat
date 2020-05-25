package com.xinlang.cat_project.item.controller;

import com.xinlang.bean.util.PageResult;
import com.xinlang.cat_project.item.VO.ItemInformationViceVO;
import com.xinlang.cat_project.item.pojo.*;
import com.xinlang.cat_project.item.service.*;
import com.xinlang.zly_xyx.cat_common.utils.AppUserUtil;
import com.xinlang.zly_xyx.log.LogAnnotation;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    public ResponseEntity<PageResult<modifyApply>> getModifyApplyAll(@RequestParam(value = "draw", defaultValue = "1") Integer draw,
                                                                     @RequestParam(value = "rows", defaultValue = "10") Integer rows,
                                                                     @RequestParam(value = "sortBy", required = false) String sortBy,
                                                                     @RequestParam(value = "desc", defaultValue = "false") Boolean desc,
                                                                     @RequestParam(required = false) Map<String, Object> params) {


        PageResult<modifyApply> result = modifyApplyService.queryList(draw, rows, sortBy, desc, params);
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
