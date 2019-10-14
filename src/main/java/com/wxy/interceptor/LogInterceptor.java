package com.wxy.interceptor;

import com.wxy.util.RequestUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.UUID;

@Slf4j
public class LogInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        long start = System.nanoTime();
        RequestUtils.startTime.set(start);
        String id = UUID.randomUUID().toString();
        RequestUtils.requestId.set(id);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        long usedTime = System.nanoTime() - RequestUtils.startTime.get();
        log.info("[requestId={},request={},response={},exception={},total={},time={}]",RequestUtils.requestId.get(),request,response,ex,usedTime/1e6d+".ms",new Date());
    }
}
