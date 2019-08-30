package com.wxy.controller;

import com.wxy.entity.Category;
import com.wxy.entity.User;
import com.wxy.service.CategoryService;
import com.wxy.util.ApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author wxy
 * @Date 19-7-24 下午1:35
 * @Description TODO
 **/
@Api(description = "分类管理")
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @ApiOperation(value = "保存分类", notes = "保存分类")
    @PostMapping("/save")
    public ApiResponse save(@ApiIgnore HttpServletRequest request, @RequestParam String name) {
        User loginUser = (User) request.getSession().getAttribute("loginUser");
        if (loginUser == null) {
            throw new RuntimeException("未登录");
        }
        int i = categoryService.saveCategory(name, loginUser.getId());
        if (i > 0) {
            return ApiResponse.success();
        }
        return ApiResponse.error();
    }

    @ApiOperation(value = "查询分类", notes = "查询分类")
    @GetMapping("/list")
    public ApiResponse findAll(@ApiIgnore HttpServletRequest request) {
        User loginUser = (User) request.getSession().getAttribute("loginUser");
        if (loginUser == null) {
            throw new RuntimeException("未登录");
        }
        List<Category> list = categoryService.findAll(loginUser.getId());
        return ApiResponse.success(list);
    }

    /**
     * 删除分类
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "删除分类", notes = "删除分类")
    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ApiResponse.success();
    }
}
