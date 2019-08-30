package com.wxy.service;

import com.wxy.entity.Category;

import java.util.List;

/**
 * @Author wxy
 * @Date 19-7-24 上午11:29
 * @Description TODO
 **/
public interface CategoryService {
    /**
     * 新增分类
     *
     * @param name
     * @param userId
     * @return
     */
    int saveCategory(String name, Long userId);

    /**
     * 查询列表
     *
     * @return
     */
    List<Category> findAll(Long userId);

    /**
     * 删除分类
     *
     * @param id
     */
    void deleteCategory(Long id);
}
