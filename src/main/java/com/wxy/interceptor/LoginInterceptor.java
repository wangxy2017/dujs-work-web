package com.wxy.interceptor;

import com.wxy.util.TokenHelper;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token = request.getHeader("token");
        if (!StringUtils.isEmpty(token) && TokenHelper.checkToken(token)) {
            return true;
        }
        throw new RuntimeException("未登录");
    }
}
