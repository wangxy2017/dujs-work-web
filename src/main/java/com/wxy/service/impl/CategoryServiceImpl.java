package com.wxy.service.impl;

import com.wxy.entity.Category;
import com.wxy.mapper.CategoryMapper;
import com.wxy.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

/**
 * @Author wxy
 * @Date 19-7-24 上午11:31
 * @Description TODO
 **/
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public int saveCategory(String name) {
        Assert.hasText(name, "The parameter name is required");
        Category category = new Category();
        category.setName(name);
        return categoryMapper.save(category);
    }

    @Override
    public List<Category> findAll() {
        return categoryMapper.queryList(null);
    }
}
