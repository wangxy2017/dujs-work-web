package com.wxy.controller;

import com.wxy.request.UserParam;
import com.wxy.service.UserService;
import com.wxy.util.ApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author wxy
 * @Date 19-8-30 上午9:32
 * @Description TODO
 **/
@Api(description = "注册控制器")
@RestController
@Slf4j
public class RegisterController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "注册", notes = "注册")
    @PostMapping("/register")
    public ApiResponse register(@RequestBody UserParam user) {
        if (userService.saveUser(user.getUsername(), user.getPassword(), user.getEmail()) > 0) {
            return ApiResponse.success();
        }
        return ApiResponse.error();
    }
}
