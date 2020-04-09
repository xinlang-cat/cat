package com.xinlang.cat_project.item.controller;

import com.xinlang.bean.projectInfo.ItemIndicators;
import com.xinlang.bean.projectInfo.ItemPersonnel;
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

import java.lang.reflect.InvocationTargetException;
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

    @ApiOperation(value = "添加项目信息")
    @LogAnnotation(module = "添加项目信息")
    @PreAuthorize("hasAnyAuthority('project:item:save')")
    @PostMapping
    public ResponseEntity<ItemInformation> saveItemInformation(@RequestBody ItemInformationVO informationVO) throws ParseException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        System.err.println(informationVO);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
        //获取当前用户ID,并SET编辑人
        Integer userId = AppUserUtil.getLoginAppUser().getId().intValue();
        ItemInformation information = informationVO.getInformation();
        //如果是修改，先删除原始数据
        if (information.getId() != null) {
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
            Date date = null;
            date = simpleDateFormat.parse(indicator.getPeriod().substring(0, 7));
            indicator.setStart_date(date);
            date = simpleDateFormat.parse(indicator.getPeriod().substring(10));
            indicator.setEnd_date(date);
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
        //添加一组空的作为其余经费的预算
        for (ItemFundBudget fundBudget : fundBudgets) {
            fundBudget.setId(null);
            fundBudget.setMoney(null);
            fundBudget.setContent(null);
            fundBudget.setRemark(null);
            fundBudget.setType(null);
        }
        itemFundBudgetService.saveFundBudgets(fundBudgets);

        //资金来源
        ItemFundSource fundSource = informationVO.getFundSource();
        fundSource.setItem_id(information.getId());
        fundSource.setFirst_party_provide(total);
        itemFundSourceService.save(fundSource);

        /*Field[] fields = fundSource.getClass().getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);
            // 获取属性的名字
            String name = field.getName();
            // 将属性的首字符大写，方便构造get，set方法
            name = name.substring(0, 1).toUpperCase() + name.substring(1);
            // 获取属性的类型
            String type = field.getGenericType().toString();
            if (type.equals("class java.lang.Integer") && !name.equals("Id") && !name.equals("Item_id") && !name.equals("First_party_provide")) {
                Method m = fundSource.getClass().getMethod("get" + name);
                Integer value = (Integer) m.invoke(fundSource);
                if (value != 0 && value.equals("")) {
                    for (ItemFundBudget fundBudget : fundBudgets) {
                        fundBudget.setId(null);
                        fundBudget.setMoney(null);
                        fundBudget.setContent(null);
                        fundBudget.setRemark(null);
                        fundBudget.setType(field.getName());
                    }
                    itemFundBudgetService.saveFundBudgets(fundBudgets);
                }
            }
        }*/

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
                                                                  @RequestParam(required = false) Map<String, Object> params) {

        PageResult<ItemInformation> result = itemInformationService.queryList(page, rows, sortBy, desc, params);
        return ResponseEntity.ok(result);
    }

    @ApiOperation(value = "查询项目信息")
    @LogAnnotation(module = "查询项目信息")
    @GetMapping("list")
    public ResponseEntity<List<ItemInformation>> getItemById(@RequestParam Map<String, Object> params) {
        List<ItemInformation> information = itemInformationService.findListByParams(params, ItemInformation.class);
        return ResponseEntity.ok(information);
    }

    @ApiOperation(value = "查询当前用户相关的项目")
    @LogAnnotation(module = "查询当前用户相关的项目")
    @GetMapping("/my")
    public ResponseEntity<List<ItemInformation>> getMyItem() {
        List<ItemInformation> information = itemInformationService.queryMyItem();
        return ResponseEntity.ok(information);
    }

    @ApiOperation(value = "修改项目信息")
    @LogAnnotation(module = "修改项目信息")
    @PreAuthorize("hasAnyAuthority('project:item:update')")
    @PutMapping
    public ResponseEntity<Void> updateItem(@RequestBody ItemInformation information) {
        itemInformationService.update(information);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "删除项目")
    @LogAnnotation(module = "删除项目")
    @PreAuthorize("hasAnyAuthority('project:item:delete')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Integer id) {
        itemInformationService.delete(id);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "查询项目信息")
    @LogAnnotation(module = "查询项目信息")
    @GetMapping("listVice")
    public ResponseEntity<List<ItemInformationVice>> getItemVice(@RequestParam Map<String, Object> params){
        List<ItemInformationVice> information = itemInformationViceService.findListByParams(params,ItemInformationVice.class);
        return ResponseEntity.ok(information);
    }
}
