package com.wxy.exception;

import com.wxy.util.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

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
        e.printStackTrace();
        return ApiResponse.error(500, e.getMessage());
    }
}
