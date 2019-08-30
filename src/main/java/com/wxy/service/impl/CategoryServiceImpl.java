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
    public int saveCategory(String name, Long userId) {
        Assert.hasText(name, "The parameter name is required");
        Assert.notNull(userId, "The parameter userId is required");
        Category category = new Category();
        category.setName(name);
        category.setUserId(userId);
        List<Category> list = categoryMapper.queryList(category);
        if (list.size() == 0) {
            return categoryMapper.save(category);
        }
        throw new RuntimeException("分类已存在");
    }

    @Override
    public List<Category> findAll(Long userId) {
        Assert.notNull(userId, "The parameter userId is required");
        Category category = new Category();
        category.setUserId(userId);
        return categoryMapper.queryList(category);
    }

    @Override
    public boolean deleteCategory(Long id) {
        Assert.notNull(id, "The parameter id is required");
        categoryMapper.delete(id);
        // 重置默认分类
//        categoryMapper.resetNoteCategory(id);
        return true;
    }
}
