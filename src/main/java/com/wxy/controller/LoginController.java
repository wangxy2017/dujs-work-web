package com.wxy.controller;

import com.wxy.entity.User;
import com.wxy.request.UserParam;
import com.wxy.service.UserService;
import com.wxy.util.ApiResponse;
import com.wxy.util.MD5Utils;
import com.wxy.util.TokenHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author wxy
 * @Date 19-8-30 上午9:32
 * @Description TODO
 **/
@Api(description = "登录控制器")
@RestController
@RequestMapping("/login")
@Slf4j
public class LoginController {

    @Autowired
    private UserService userService;

    /**
     * 登录
     *
     * @param user
     * @return
     */
    @ApiOperation(value = "登录", notes = "登录")
    @PostMapping("/login")
    public ApiResponse login(@RequestBody UserParam user) {
        User user1 = userService.queryByUsername(user.getUsername());
        if (user1 != null && user1.getPassword().equals(MD5Utils.MD5Encode(user.getPassword(), user1.getSalt()))) {
            String token = TokenHelper.createToken(user1.getUsername(), user1.getId());
            Map<String, Object> data = new HashMap<>();
            data.put("token", token);
            data.put("userId", user1.getId());
            return ApiResponse.success(data);
        }
        return ApiResponse.error(-1, "用户名或密码错误");
    }
}
