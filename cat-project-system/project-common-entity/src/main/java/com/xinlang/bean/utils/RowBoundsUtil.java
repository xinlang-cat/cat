package com.xinlang.bean.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;

import java.util.Map;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019-11-24
 */
public class RowBoundsUtil {

    public static RowBounds getRowBounds(Map<String,Object> params){
        int start = 0;
        String p1 = (String)params.get("start");
        if(!StringUtils.isBlank(p1)){
            start = Integer.valueOf(p1);
        }
        int length = 10;
        String p2 = (String)params.get("length");
        if(!StringUtils.isBlank(p2)){
            length = Integer.valueOf(p2);
        }
        return new RowBounds(start,length);
    }
}
