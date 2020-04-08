package com.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    /**
     * 日期转字符串
     * @param date
     * @param pattern
     * @return
     */
    public  static String date2String(Date date, String pattern){
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        String format = sdf.format(date);
        return format;
    }

    /**
     * 字符串转日期
     * @param date
     * @param pattern
     * @return
     * @throws ParseException
     */
    public  static Date string2Date(String date, String pattern) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Date parse = sdf.parse(date);
        return parse;
    }
}
