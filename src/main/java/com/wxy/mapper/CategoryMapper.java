package com.wxy.mapper;

import com.wxy.core.BaseMapper;
import com.wxy.entity.Category;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryMapper extends BaseMapper<Category> {

    /**
     * 重置笔记分类
     *
     * @param id
     */
    void resetNoteCategory(Long id);
}
