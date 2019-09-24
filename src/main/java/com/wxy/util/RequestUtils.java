package com.wxy.util;

/**
 * @Author wxy
 * @Date 19-9-24 下午1:46
 * @Description TODO
 **/
public class RequestUtils {
    public static ThreadLocal<Long> startTime = new ThreadLocal<>();

    public static ThreadLocal<String> requestId = new ThreadLocal<>();
}
