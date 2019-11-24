package com.xinlang.bean.utils;

import org.apache.ibatis.session.RowBounds;

import java.util.Map;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019-11-24
 */
public class RowBoundsUtil {

    public static RowBounds getRowBounds(Map<String,Object> params){
        Integer start = 0;
        if(!params.get("start").equals("")){
            start = (Integer)params.get("start");
        }
        Integer length = 0;
        if(!params.get("length").equals("")){
            length = (Integer)params.get("length");
        }
        return new RowBounds(start,length);
    }
}
