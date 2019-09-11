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
     * 查询分类列表
     *
     * @return
     */
    List<Category> findAll(Long userId, String name);

    /**
     * 删除分类
     *
     * @param id
     */
    boolean deleteCategory(Long id, Long userId);

    /**
     * 查询回收站
     *
     * @param userId
     * @return
     */
    Category findRecycle(Long userId);

    /**
     * 查询默认分类
     *
     * @param userId
     * @return
     */
    Category findDefault(Long userId);

    /**
     * 修改分类
     * @param id
     * @param name
     * @return
     */
    int updateCategory(Long id, String name);
}
