package com.wxy.service.impl;

import com.wxy.constanst.CategoryConstants;
import com.wxy.entity.Category;
import com.wxy.mapper.CategoryMapper;
import com.wxy.service.CategoryService;
import com.wxy.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author wxy
 * @Date 19-7-24 上午11:31
 * @Description TODO
 **/
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private NoteService noteService;

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
    public List<Category> findAll(Long userId, String name) {
        Assert.notNull(userId, "The parameter userId is required");
        Category category = new Category();
        category.setUserId(userId);
        category.setName(name);
        return categoryMapper.queryList(category);
    }

    @Override
    public boolean deleteCategory(Long id, Long userId) {
        Assert.notNull(id, "The parameter id is required");
        Assert.notNull(userId, "The parameter userId is required");
        categoryMapper.delete(id);
        // 重置默认分类
        Map<String, Object> param = new HashMap<>();
        param.put("currId", id);
        param.put("refId", findDefault(userId));
        categoryMapper.resetNoteCategory(param);
        return true;
    }

    @Override
    public Category findRecycle(Long userId) {
        Assert.notNull(userId, "The parameter userId is required");
        List<Category> list = findAll(userId, CategoryConstants.RECYCLE);
        if (list != null && list.size() == 1) {
            return list.get(0);
        }
        throw new RuntimeException("数据异常");
    }

    @Override
    public Category findDefault(Long userId) {
        Assert.notNull(userId, "The parameter userId is required");
        List<Category> list = findAll(userId, CategoryConstants.DEFAULT);
        if (list != null && list.size() == 1) {
            return list.get(0);
        }
        throw new RuntimeException("数据异常");
    }
}
