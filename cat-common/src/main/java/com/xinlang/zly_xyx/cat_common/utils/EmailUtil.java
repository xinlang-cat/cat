package com.xinlang.zly_xyx.cat_common.utils;

import org.apache.commons.lang.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019/7/22
 */
public class EmailUtil {

    private static String REGEX =  "^([a-z0-9A-Z]+[-|//.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?//.)+[a-zA-Z]{2,}$";
    private static Pattern P = Pattern.compile(REGEX);
    /**
     * 验证输入的邮箱格式是否符合
     * @param email
     * @return 是否合法
     */
    public static boolean emailFormat(String email) {
        if(StringUtils.isBlank(email)){
            return Boolean.FALSE;
        }
        Matcher mat = P.matcher(email);
        return mat.matches();
    }
}
