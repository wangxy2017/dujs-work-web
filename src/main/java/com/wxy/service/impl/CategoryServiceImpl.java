package com.wxy.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wxy.constanst.NoteCategories;
import com.wxy.entity.Category;
import com.wxy.mapper.CategoryMapper;
import com.wxy.service.CategoryService;
import com.wxy.util.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        if (CollectionUtils.isEmpty(list)) {
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
        // 过滤回收站
        List<Category> list = categoryMapper.queryList(category);
        return list.stream().filter(n -> !n.getName().equals(NoteCategories.RECYCLE)).collect(Collectors.toList());

    }

    @Override
    public boolean deleteCategory(Long id, Long userId) {
        Assert.notNull(id, "The parameter id is required");
        Assert.notNull(userId, "The parameter userId is required");
        Category c = categoryMapper.queryById(id);
        if (c != null && !c.getName().equals(NoteCategories.RECYCLE) && !c.getName().equals(NoteCategories.DEFAULT)) {
            categoryMapper.delete(id);
            // 重置默认分类
            Map<String, Object> param = new HashMap<>();
            param.put("currId", id);
            param.put("refId", findDefault(userId).getId());
            categoryMapper.resetNoteCategory(param);
            return true;
        }
        throw new RuntimeException("系统分类不能删除");
    }

    @Override
    public Category findRecycle(Long userId) {
        Assert.notNull(userId, "The parameter userId is required");
        Category category = new Category();
        category.setUserId(userId);
        category.setName(NoteCategories.RECYCLE);
        List<Category> list = categoryMapper.queryList(category);
        if (list != null && list.size() == 1) {
            return list.get(0);
        }
        throw new RuntimeException("没有回收站");
    }

    @Override
    public Category findDefault(Long userId) {
        Assert.notNull(userId, "The parameter userId is required");
        Category category = new Category();
        category.setUserId(userId);
        category.setName(NoteCategories.DEFAULT);
        List<Category> list = categoryMapper.queryList(category);
        if (list != null && list.size() == 1) {
            return list.get(0);
        }
        throw new RuntimeException("没有默认笔记");
    }

    @Override
    public int updateCategory(Long id, String name) {
        Assert.notNull(id, "The parameter id is required");
        Assert.hasText(name, "The parameter name is required");
        Category c = categoryMapper.queryById(id);
        if (c != null && !c.getName().equals(NoteCategories.RECYCLE) && !c.getName().equals(NoteCategories.DEFAULT)) {
            c.setName(name);
            return categoryMapper.update(c);
        }
        throw new RuntimeException("修改失败");
    }

    @Override
    public PageModel<Category> queryPageList(Long userId, String name, Integer pageNum, Integer pageSize) {
        Assert.notNull(userId, "The parameter userId is required");
        Assert.notNull(pageNum, "The parameter pageNum is required");
        Assert.notNull(pageSize, "The parameter pageSize is required");

        PageHelper.startPage(pageNum, pageSize);
        Category category = new Category();
        category.setUserId(userId);
        category.setName(name);
        Page<Category> page = categoryMapper.queryPageList(category);
        return new PageModel<>(page);
    }
}
