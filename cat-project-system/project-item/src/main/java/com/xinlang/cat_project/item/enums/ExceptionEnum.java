package com.xinlang.cat_project.item.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author 梁应昌
 * 2019/9/27
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum  ExceptionEnum {

    DATA_NOT_FOUND(500,"数据不存在"),
    ITEM_NOT_FOUND(404,"项目不存在"),
    ITEM_SAVE_ERROR(500,"新增项目失败"),
    ITEM_UPDATE_ERROR(500,"项目更改失败"),
    ITEM_DELETE_ERROR(500,"项目删除失败"),
    ITEM_DISCARD_ERROR(500,"项目废弃失败"),

    PLAN_SAVE_ERROR(500,"新增研发内容失败"),
    DATE_FORMAT_ERROR(500,"日期格式错误"),
    PLAN_UPDATE_ERROR(500,"研发内容更改失败"),
    PLAN_DELETE_ERROR(500,"研发内容删除失败"),

    TARGET_SAVE_ERROR(500,"新增指标失败"),
    TARGET_UPDATE_ERROR(500,"指标更改失败"),
    TARGET_DELETE_ERROR(500,"指标删除失败"),
    CRITERIA_NOT_DELETED(500,"标准未删除"),

    ITEM_USER_SAVE_ERROR(500,"新增项目人员失败"),
    ITEM_USER_UPDATE_ERROR(500,"更改项目人员失败"),
    ITEM_USER_DELETE_ERROR(500,"删除项目人员失败"),

    FUND_SAVE_ERROR(500,"新增资金失败"),
    FUND_UPDATE_ERROR(500,"更改资金失败"),
    FUND_DELETE_ERROR(500,"删除资金失败"),

    BUDGET_SAVE_ERROR(500,"新增预算失败"),
    BUDGET_UPDATE_ERROR(500,"更改预算失败"),
    BUDGET_DELETE_ERROR(500,"删除预算失败"),

    USER_INFO_NOT_PERFECT(500,"用户信息未完善"),

    SAVE_ERROR(500,"保存失败"),
    UPDATE_ERROR(500,"更改失败"),
    DELETE_ERROR(500,"删除失败"),
    ;
    private int code;
    private String msg;
}
