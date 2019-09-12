package com.wxy.interceptor;

import com.wxy.util.TokenHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token = request.getHeader("Token");
        if (!StringUtils.isEmpty(token) && TokenHelper.checkToken(token)) {
            return true;
        }
        throw new RuntimeException("未登录");
    }
}
