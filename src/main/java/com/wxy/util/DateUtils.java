package com.wxy.util;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @Author wangxy
 * @Date 2019/6/17 10:29
 * @Description 日期工具类
 **/
public class DateUtils {

    public static final String DATE_PATTERN = "yyyy-MM-dd";
    public static final String TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * 获取标准 UTC 时间
     *
     * @return
     */
    public static String utcTime() {
        String utcTime = LocalDateTime.now().minusHours(8).toString();
        utcTime = utcTime.lastIndexOf(".") == -1 ? utcTime + "Z" : utcTime.substring(0, utcTime.lastIndexOf(".")) + "Z";
        return utcTime;
    }

    /**
     * 格式化时间
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String format(Date date, String pattern) {
        if (date == null || pattern == null) {
            return null;
        }
        return new SimpleDateFormat(pattern).format(date);
    }

    /**
     * 获取当前日期
     *
     * @return
     */
    public static String nowDate() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(DATE_PATTERN));
    }

    /**
     * 获取当前时间
     *
     * @return
     */
    public static String nowTime() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(TIME_PATTERN));
    }

    /**
     * 获取秒
     *
     * @param before
     * @param after
     * @return
     */
    public static long getSeconds(Date before, Date after) {
        if (before == null || after == null) {
            return 0;
        }
        return (after.getTime() - before.getTime()) / 1000;
    }

    public static String nowTimeUTC() {
        return LocalDateTime.now().minusHours(8).format(DateTimeFormatter.ofPattern(TIME_PATTERN));
    }

    public static String nowDateUTC() {
        return LocalDateTime.now().minusHours(8).format(DateTimeFormatter.ofPattern(DATE_PATTERN));
    }
}
