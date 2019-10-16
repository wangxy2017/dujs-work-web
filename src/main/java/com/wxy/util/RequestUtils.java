package com.wxy.util;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * @Author wxy
 * @Date 19-9-24 下午1:46
 * @Description TODO
 **/
public class RequestUtils {
    private static ThreadLocal<String> threadRequestId = new ThreadLocal<>();

    public static String getOrCreateRequestId() {
        if (threadRequestId.get() == null) {
            threadRequestId.set(UUID.randomUUID().toString());
        }
        return threadRequestId.get();
    }

    public static String getRemoteIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
