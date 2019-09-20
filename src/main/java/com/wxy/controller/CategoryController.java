package com.wxy.controller;

import com.wxy.entity.Category;
import com.wxy.request.CategoryParam;
import com.wxy.service.CategoryService;
import com.wxy.util.ApiResponse;
import com.wxy.util.TokenHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public ApiResponse save(@RequestBody CategoryParam category) {
        int i = categoryService.saveCategory(category.getName(), TokenHelper.getUserId());
        if (i > 0) {
            return ApiResponse.success();
        }
        return ApiResponse.error();
    }

    @ApiOperation(value = "查询分类", notes = "查询分类")
    @GetMapping("/list")
    public ApiResponse findAll() {
        List<Category> list = categoryService.findAll(TokenHelper.getUserId(), null);
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
        categoryService.deleteCategory(id, TokenHelper.getUserId());
        return ApiResponse.success();
    }

    /**
     * 修改分类
     *
     * @param category
     * @return
     */
    @ApiOperation(value = "修改分类", notes = "修改分类")
    @PostMapping("/update")
    public ApiResponse delete(@RequestBody CategoryParam category) {
        categoryService.updateCategory(category.getId(), category.getName());
        return ApiResponse.success();
    }
}
