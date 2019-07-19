package com.xinlang.zly_xyx.cat_common.utils;

import java.util.Map;

import org.apache.commons.collections4.MapUtils;
import org.springframework.util.CollectionUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PageUtil {
    /**
     * 分页参数，起始位置，从0开始
     */
    public static final String START = "start";
    /**
     * 分页参数，每页数据条数
     */
    public static final String LENGTH = "length";

    /**
     * 分页方法
     *
     * @param params
     * @param required
     */
    public static void pageParamConver(Map<String, Object> params, boolean required) {
        if (required) {// 分页参数必填时，校验参数
            if (params == null || !params.containsKey(START) || !params.containsKey(LENGTH)) {
                throw new IllegalArgumentException("请检查分页参数," + START + "," + LENGTH);
            }
        }
        if (!CollectionUtils.isEmpty(params)) {
            if (params.containsKey(START)) {
                Integer start = MapUtils.getInteger(params, START);
                if (start < 0) {
                    log.error("start：{}，重置为0", start);
                    start = 0;
                }
                params.put(START, start);
            }

        }

        if (params.containsKey(LENGTH)) {
            Integer length = MapUtils.getInteger(params, LENGTH);
            if (length < 0) {
                log.error("length：{}，重置为0", length);
                length = 0;
            }
            params.put(LENGTH, length);
        }
    }


    }
}