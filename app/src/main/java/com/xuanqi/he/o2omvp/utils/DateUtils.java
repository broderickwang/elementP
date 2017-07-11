package com.xuanqi.he.o2omvp.utils;

import java.text.SimpleDateFormat;

/**
 * Created by Administrator on 2017/4/27.
 */

public class DateUtils {

    //获取当前时间
    public static String getCurrentTime(){
        long l = System.currentTimeMillis();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = format.format(l);
        return time;
    }
}
