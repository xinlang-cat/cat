package com.xinlang.zly_xyx.cat_common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019/7/22
 */
public class PhoneUtil {

     private static String REGEX = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[013678])|(18[0,5-9]))\\d{8}$";
     private static Pattern P = Pattern.compile(REGEX);

     public static boolean checkPhone(String phone) {
          if (phone == null || phone.length() != 11) {
               return Boolean.FALSE;
          }
          Matcher m = P.matcher(phone);
          return m.matches();
     }
}
