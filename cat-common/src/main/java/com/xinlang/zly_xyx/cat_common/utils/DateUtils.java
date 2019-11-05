package com.xinlang.zly_xyx.cat_common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类
 * @author 梁应昌
 * 2019/9/30
 */
public class DateUtils {

    public static String getCaexpoNum(int year){
        int num=year-2004+1;
        return num+"";
    }
    public static String getCaexpoNum(){
        Calendar cal=Calendar.getInstance();
        int year=cal.get(Calendar.YEAR);
        int num=year-2004+1;

        return num+"";
    }
    public static String dateToString(Date date, String pattern){
        SimpleDateFormat format=new SimpleDateFormat(pattern);

        return format.format(date);
    }

    public static Date stringToDate(String date,String pattern) throws ParseException {
        SimpleDateFormat format=new SimpleDateFormat(pattern);

        return format.parse(date);
    }

    public static int getYear(){
        Calendar cal=Calendar.getInstance();
        int year=cal.get(Calendar.YEAR);
        return year;
    }
}
