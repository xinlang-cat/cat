package com.xinlang.zly.project_user.controller;

import com.xinlang.zly.project_user.service.ICustomerServiceStaffService;
import com.xinlang.zly_xyx.common.Page;
import com.xinlang.zly_xyx.log.LogAnnotation;
import com.xinlang.zly_xyx.user.CustomerServiceStaff;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/customer-service-staff")
public class CustomerServiceStaffController {
    @Autowired
    private ICustomerServiceStaffService customerServiceStaffService;

    @PostMapping
    @LogAnnotation(module = "添加客服人员")
    @ApiOperation(value = "添加客服人员")
    public CustomerServiceStaff save(@RequestBody CustomerServiceStaff customerServiceStaff) {
        customerServiceStaffService.save(customerServiceStaff);
        return customerServiceStaff;
    }

    @PutMapping
    @LogAnnotation(module = "修改客服人员")
    @ApiOperation(value = "修改客服人员")
    public CustomerServiceStaff update(@RequestBody CustomerServiceStaff customerServiceStaff) {
        customerServiceStaffService.update(customerServiceStaff);
        return customerServiceStaff;
    }

    @GetMapping("/list")
    @LogAnnotation(module = "查询客服人员列表")
    @ApiOperation(value = "查询客服人员列表")
    public List<CustomerServiceStaff> findListByParams(@RequestParam Map<String, Object> params) {
        return customerServiceStaffService.findListByParams(params, CustomerServiceStaff.class);
    }

    @GetMapping("/page")
    @LogAnnotation(module = "查询客服人员分页")
    @ApiOperation(value = "查询客服人员分页")
    public Page<CustomerServiceStaff> findPageByParams(@RequestParam Map<String, Object> params) {
        return customerServiceStaffService.findPageByParams(params, CustomerServiceStaff.class);
    }

    @DeleteMapping("/{id}")
    @LogAnnotation(module = "删除客服人员")
    @ApiOperation(value = "删除客服人员")
    public void delete(@PathVariable Integer id) {
        customerServiceStaffService.delete(id);
    }

    @GetMapping("/all")
    @LogAnnotation(module = "查询客服人员列表")
    @ApiOperation(value = "查询客服人员列表")
    public List<CustomerServiceStaff> findAll() {
        return customerServiceStaffService.findAll();
    }
}
