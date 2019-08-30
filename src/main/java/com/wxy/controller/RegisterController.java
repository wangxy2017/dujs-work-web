package com.wxy.controller;

import com.wxy.entity.User;
import com.wxy.request.UserParam;
import com.wxy.service.UserService;
import com.wxy.util.ApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 注册
     *
     * @param user
     * @return
     */
    @ApiOperation(value = "注册", notes = "注册")
    @PostMapping("/register")
    public ApiResponse register(@RequestBody UserParam user) {
        if (userService.saveUser(user.getUsername(), user.getPassword(), user.getEmail()) > 0) {
            return ApiResponse.success();
        }
        return ApiResponse.error();
    }

    /**
     * 检查账号
     *
     * @param username
     * @return
     */
    @ApiOperation(value = "检查账号是否存在", notes = "检查账号是否存在")
    @GetMapping("/checkUser")
    public ApiResponse checkUser(@RequestParam String username) {
        User user = userService.queryByUsername(username);
        if (user == null) {
            return ApiResponse.success("用户不存在");
        }
        return ApiResponse.error();
    }

    /**
     * 检查邮箱
     *
     * @param email
     * @return
     */
    @ApiOperation(value = "检查邮箱是否存在", notes = "检查邮箱是否存在")
    @GetMapping("/checkEmail")
    public ApiResponse checkEmail(@RequestParam String email) {
        User user = userService.queryByEmail(email);
        if (user == null) {
            return ApiResponse.success("邮箱不存在");
        }
        return ApiResponse.error();
    }
}
