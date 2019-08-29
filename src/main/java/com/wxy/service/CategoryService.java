package com.wxy.service;

import com.wxy.entity.Category;

import java.util.List;

/**
 * @Author wxy
 * @Date 19-7-24 上午11:29
 * @Description TODO
 **/
public interface CategoryService {
    int saveCategory(String name);

    List<Category> findAll();
}
