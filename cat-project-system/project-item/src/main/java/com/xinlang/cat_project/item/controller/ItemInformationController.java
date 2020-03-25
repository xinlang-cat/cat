package com.xinlang.cat_project.item.controller;

import com.xinlang.cat_project.item.VO.ItemInformationVO;
import com.xinlang.cat_project.item.pojo.*;
import com.xinlang.cat_project.item.service.*;
import com.xinlang.zly_xyx.cat_common.utils.AppUserUtil;
import com.xinlang.zly_xyx.log.LogAnnotation;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Transactional
@RestController
@RequestMapping("/item/information")
public class ItemInformationController {

    @Autowired
    private IItemInformationService itemInformationService;
    @Autowired
    private IItemIndicatorsService itemIndicatorsService;
    @Autowired
    private IItemSchedulingService itemSchedulingService;
    @Autowired
    private IItemPersonnelService itemPersonnelService;
    @Autowired
    private IItemFundBudgetService itemFundBudgetService;
    @Autowired
    private IItemFundSourceService itemFundSourceService;
    @Autowired
    private IItemContactWayService itemContactWayService;

    @ApiOperation(value = "添加项目信息")
    @LogAnnotation(module = "添加项目信息")
    @PreAuthorize("hasAnyAuthority('project:item:save')")
    @PostMapping
    public ResponseEntity<ItemInformation> saveItemInformation(@RequestBody ItemInformationVO informationVO) throws ParseException {
        System.err.println(informationVO);
        //获取当前用户ID,并SET编辑人
        Integer userId = AppUserUtil.getLoginAppUser().getId().intValue();
        ItemInformation information = informationVO.getInformation();

        if(information.getId()!=null){
            itemInformationService.delete(information.getId());

            ItemIndicators indicator = new ItemIndicators();
            indicator.setItem_id(information.getId());
            itemIndicatorsService.deleteByAttribute(indicator);

            ItemScheduling scheduling = new ItemScheduling();
            scheduling.setItem_id(information.getId());
            itemSchedulingService.deleteByAttribute(scheduling);

            ItemPersonnel personnel = new ItemPersonnel();
            personnel.setItem_id(information.getId());
            itemPersonnelService.deleteByAttribute(personnel);

            ItemFundBudget fundBudget = new ItemFundBudget();
            fundBudget.setItem_id(information.getId());
            itemFundBudgetService.deleteByAttribute(fundBudget);

            ItemFundSource fundSource = new ItemFundSource();
            fundSource.setItem_id(information.getId());
            itemFundSourceService.deleteByAttribute(fundSource);

            ItemContactWay contactWay = new ItemContactWay();
            contactWay.setItem_id(information.getId());
            itemContactWayService.deleteByAttribute(contactWay);

            information.setId(null);
        }

        information.setEdit_user_id(userId);
        information.setEdit_date(new Date());
        if (!information.getPeriod().equals("")) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
            Date date = null;
            date = simpleDateFormat.parse(information.getPeriod().substring(0, 7));
            information.setStart_date(date);
            date = simpleDateFormat.parse(information.getPeriod().substring(10));
            information.setEnd_date(date);
        }
        itemInformationService.save(information);
        //指标
        List<ItemIndicators> indicators = informationVO.getIndicators();
        for (ItemIndicators indicator : indicators) {
            indicator.setItem_id(information.getId());
        }
        itemIndicatorsService.saveIndicators(indicators);
        List<ItemIndicators> achievements = informationVO.getAchievements();
        for (ItemIndicators achievement : achievements) {
            achievement.setItem_id(information.getId());
        }
        itemIndicatorsService.saveIndicators(achievements);
        //计划
        List<ItemScheduling> schedulings = informationVO.getScheduling();
        for (ItemScheduling scheduling : schedulings) {
            scheduling.setItem_id(information.getId());
        }
        itemSchedulingService.saveSchedulings(schedulings);
        //人员
        List<ItemPersonnel> personnels = informationVO.getPersonnels();
        for (ItemPersonnel personnel : personnels) {
            personnel.setItem_id(information.getId());
        }
        itemPersonnelService.savePersonnels(personnels);
        //资金预算
        List<ItemFundBudget> fundBudgets = informationVO.getFundBudgets();
        int total = 0;
        for (ItemFundBudget fundBudget : fundBudgets) {
            if (fundBudget.getMoney() != null) {
                total += fundBudget.getMoney();
            }
            fundBudget.setItem_id(information.getId());
        }
        itemFundBudgetService.saveFundBudgets(fundBudgets);
        //资金来源
        ItemFundSource fundSource = informationVO.getFundSource();
        fundSource.setItem_id(information.getId());
        fundSource.setFirst_party_provide(total);
        itemFundSourceService.save(fundSource);
        //联系方式
        List<ItemContactWay> contactWays = informationVO.getContactWays();
        for (ItemContactWay contactWay : contactWays) {
            contactWay.setItem_id(information.getId());
        }
        itemContactWayService.saveContactWays(contactWays);

        return ResponseEntity.status(HttpStatus.CREATED).body(informationVO.getInformation());
    }

    /**
     * 查询项目，分页查询
     * @param page 当前页
     * @param rows 每页大小
     * @param sortBy 排序字段 (预留，暂时没用到)
     * @param desc 是否为降序 (预留，暂时没用到)
     * @param params 参数
     * @return
     */
    @LogAnnotation(module = "获取项目列表")
    @GetMapping("/page")
    public ResponseEntity<PageResult<ItemInformation>> getItemAll(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                                            @RequestParam(value = "rows", defaultValue = "10") Integer rows,
                                                            @RequestParam(value = "sortBy", required = false) String sortBy,
                                                            @RequestParam(value = "desc", defaultValue = "false") Boolean desc,
                                                            @RequestParam(required = false) Map<String, Object> params){

        PageResult<ItemInformation> result = itemInformationService.queryList(page,rows,sortBy,desc,params);
        return ResponseEntity.ok(result);
    }

    @ApiOperation(value = "查询项目信息")
    @LogAnnotation(module = "查询项目信息")
    @GetMapping("list")
    public ResponseEntity<List<ItemInformation>> getItemById(@RequestParam Map<String, Object> params){
        List<ItemInformation> information = itemInformationService.findListByParams(params,ItemInformation.class);
        return ResponseEntity.ok(information);
    }

    @ApiOperation(value = "查询当前用户相关的项目")
    @LogAnnotation(module = "查询当前用户相关的项目")
    @GetMapping("/my")
    public ResponseEntity<List<ItemInformation>> getMyItem(){
        List<ItemInformation> information = itemInformationService.queryMyItem();
        return ResponseEntity.ok(information);
    }

    @ApiOperation(value = "修改项目信息")
    @LogAnnotation(module = "修改项目信息")
    @PreAuthorize("hasAnyAuthority('project:item:update')")
    @PutMapping
    public ResponseEntity<Void> updateItem(@RequestBody ItemInformation information){
        itemInformationService.update(information);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "删除项目")
    @LogAnnotation(module = "删除项目")
    @PreAuthorize("hasAnyAuthority('project:item:delete')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Integer id){
        itemInformationService.delete(id);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
