package com.wxy.controller;

import com.wxy.request.UserParam;
import com.wxy.service.UserService;
import com.wxy.util.ApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author wxy
 * @Date 19-7-24 下午5:42
 * @Description TODO
 **/
@Api(description = "用户管理")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "保存用户", notes = "保存用户")
    @PostMapping("/save")
    public ApiResponse save(@RequestBody UserParam user) {
        if (userService.saveUser(user.getUsername(), user.getPassword(), user.getEmail()) > 0) {
            return ApiResponse.success();
        }
        return ApiResponse.error();
    }
}
