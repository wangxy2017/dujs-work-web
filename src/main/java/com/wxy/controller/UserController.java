package com.wxy.controller;

import com.wxy.entity.User;
import com.wxy.request.IdeaParam;
import com.wxy.request.UserParam;
import com.wxy.service.UserService;
import com.wxy.util.ApiResponse;
import com.wxy.util.TokenHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
     * @param user
     * @return
     */
    @ApiOperation(value = "修改密码", notes = "修改密码")
    @PostMapping("/password")
    public ApiResponse password(@RequestBody UserParam user) {
        int update = userService.updatePassword(TokenHelper.getUserId(), user.getPassword(), user.getNew_password());
        if (update > 0) {
            return ApiResponse.success();
        }
        return ApiResponse.error();
    }

    /**
     * 修改基本信息
     *
     * @param user
     * @return
     */
    @ApiOperation(value = "修改基本信息", notes = "修改基本信息")
    @PostMapping("/update")
    public ApiResponse update(@RequestBody UserParam user) {
        int update = userService.updateUser(TokenHelper.getUserId(), user.getEmail(), user.getNickName());
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

    /**
     * 意见反馈
     *
     * @param idea
     * @return
     */
    @ApiOperation(value = "意见反馈", notes = "意见反馈")
    @PostMapping("/idea")
    public ApiResponse giveIdea(@RequestBody IdeaParam idea) {
        User user = userService.queryById(TokenHelper.getUserId());
        boolean bool = userService.giveIdea(user.getEmail(), idea.getContent());
        if (bool) {
            return ApiResponse.success();
        }
        return ApiResponse.error();
    }
}
