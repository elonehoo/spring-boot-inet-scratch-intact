package com.inet.code.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * 日期工具类
 *
 * @author HCY
 * @since 2020/12/24 9:52 下午
 */
public class DateUtils {
    /**
     * 从今天开始前七天的日期
     *
     * @author HCY
     * @since 2020/12/24 9:52 下午
     * @return java.lang.String[]
    */
    public static  String [] getBeforeSevenDay(){
        String [] arr = new String[7];
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = null;
        for (int i = 0 ; i < 7 ; i++){
            c=Calendar.getInstance();
            c.add(Calendar.DAY_OF_MONTH, - i - 1);
            arr[6 - i] =sdf.format(c.getTime());

        }
        return arr;
    }
}
