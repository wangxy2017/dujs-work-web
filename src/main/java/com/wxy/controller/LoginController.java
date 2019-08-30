package com.wxy.controller;

import com.wxy.entity.User;
import com.wxy.request.UserParam;
import com.wxy.service.UserService;
import com.wxy.util.ApiResponse;
import com.wxy.util.MD5Utils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author wxy
 * @Date 19-8-30 上午9:32
 * @Description TODO
 **/
@Api(description = "登录控制器")
@RestController
@Slf4j
public class LoginController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "登录", notes = "登录")
    @PostMapping("/login")
    public ApiResponse login(@ApiIgnore HttpServletRequest request, @RequestBody UserParam user) {
        User user1 = userService.queryByUsername(user.getUsername());
        if (user1 != null && user1.getPassword().equals(MD5Utils.MD5Encode(user.getPassword(), user1.getSalt()))) {
            request.getSession().setAttribute("loginUser", user1);
            return ApiResponse.success();
        }
        return ApiResponse.error();
    }
}
