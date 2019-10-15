package com.wxy.exception;

import com.wxy.util.ApiResponse;
import com.wxy.util.RequestUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

/**
 * @Author wxy
 * @Date 19-7-1 下午1:55
 * @Description 全局异常处理
 **/
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler()
    @ResponseBody
    public ApiResponse exceptionHandle(Exception e) {
        log.error("请求异常：requestId = {},exception = {}", RequestUtils.getOrCreateRequestId(), e.getMessage());
        HashMap<String, Object> data = new HashMap<>();
        data.put("requestId", RequestUtils.getOrCreateRequestId());
        return ApiResponse.error(500, e.getMessage(), data);
    }
}
