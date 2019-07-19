package com.xinlang.zly_xyx.log;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 日志对象
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class log {
    private Long id;
    private String username;
    //模块名
    private String module;
    //参数值
    private  String params;
    private  String remark;
    //是否执行成功
    private Boolean flag;
    private  String createTime;
    //日志信息
    private  String infomsg;

}
