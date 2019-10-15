package com.wxy.util;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * @Author wxy
 * @Date 19-9-24 下午1:46
 * @Description TODO
 **/
public class RequestUtils {
    private static volatile ThreadLocal<String> threadRequestId = new ThreadLocal<>();

    public static String getOrCreateRequestId() {
        if (threadRequestId.get() == null) {
            synchronized (RequestUtils.class) {
                if (threadRequestId.get() == null) {
                    threadRequestId.set(UUID.randomUUID().toString());
                }
            }
        }
        return threadRequestId.get();
    }

    public static String getRemoteIp(HttpServletRequest request) {
        String ip = request.getHeader("Client-Ip");
        if (StringUtils.isBlank(ip)) {
            ip = request.getHeader("X-Bce-Client-Ip");
        }
        if (StringUtils.isBlank(ip)) {
            ip = request.getHeader("X-Forwarded-For");
        }
        if (StringUtils.isBlank(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
