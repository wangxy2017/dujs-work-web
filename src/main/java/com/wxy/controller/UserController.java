package com.wxy.controller;

import com.wxy.entity.User;
import com.wxy.request.UserParam;
import com.wxy.service.UserService;
import com.wxy.util.ApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;

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

    /**
     * 修改密码
     *
     * @param request
     * @param user
     * @return
     */
    @ApiOperation(value = "修改密码", notes = "修改密码")
    @PostMapping("/password")
    public ApiResponse password(@ApiIgnore HttpServletRequest request, @RequestBody UserParam user) {
        User loginUser = (User) request.getSession().getAttribute("loginUser");
        if (loginUser == null) {
            throw new RuntimeException("未登录");
        }
        int update = userService.updatePassword(loginUser.getId(), user.getPassword(), user.getNew_password());
        if (update > 0) {
            return ApiResponse.success();
        }
        return ApiResponse.error();
    }

    /**
     * 修改基本信息
     *
     * @param request
     * @param user
     * @return
     */
    @ApiOperation(value = "修改基本信息", notes = "修改基本信息")
    @PostMapping("/update")
    public ApiResponse update(@ApiIgnore HttpServletRequest request, @RequestBody UserParam user) {
        User loginUser = (User) request.getSession().getAttribute("loginUser");
        if (loginUser == null) {
            throw new RuntimeException("未登录");
        }
        int update = userService.updateUser(loginUser.getId(), user.getEmail(), user.getNickName());
        if (update > 0) {
            return ApiResponse.success();
        }
        return ApiResponse.error();
    }

    /**
     * 查询基本信息
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "查询基本信息", notes = "查询基本信息")
    @GetMapping("/query/{id}")
    public ApiResponse query(@PathVariable Long id) {
        return ApiResponse.success(userService.queryById(id));
    }
}
