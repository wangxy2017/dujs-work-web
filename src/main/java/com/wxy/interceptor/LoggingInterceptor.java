package com.wxy.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Slf4j
public class LoggingInterceptor implements HandlerInterceptor {

    private static ThreadLocal<Long> startTime = new ThreadLocal<>();

    private static ThreadLocal<String> requestId = new ThreadLocal<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        long start = System.nanoTime();
        startTime.set(start);
        String id = UUID.randomUUID().toString();
        requestId.set(id);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        long usedTime = System.nanoTime() - startTime.get();
        log.info("[requestId={},request={},response={},exception={},total={}]",requestId.get(),request,response,ex,usedTime/1e6d+".ms");
    }
}
