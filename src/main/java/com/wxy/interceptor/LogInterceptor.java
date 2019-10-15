package com.wxy.interceptor;

import com.wxy.util.DateUtils;
import com.wxy.util.RequestUtils;
import com.wxy.util.TokenHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Slf4j
public class LogInterceptor implements HandlerInterceptor {

    @Override
    public void afterCompletion(
            HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        String requestId = RequestUtils.getOrCreateRequestId();
        String ip = RequestUtils.getRemoteIp(request);
        String uri = request.getRequestURI();
        String method = request.getMethod();
        Long userId = TokenHelper.getUserId();
        String callTime = DateUtils.nowTime();
        log.debug("requestId: {}, ip: {}, uri: {}, method: {}, exception: {}, userId: {}, callTime: {}", requestId, ip,
                uri, method, ex.getMessage(), userId, callTime);
    }
}
