package com.wxy.mapper;

import com.wxy.core.BaseMapper;
import com.wxy.entity.Bookmark;
import org.springframework.stereotype.Repository;

@Repository
public interface BookmarkMapper extends BaseMapper<Bookmark> {

    /**
     * 删除书签
     *
     * @param userId
     */
    void deleteByUserId(Long userId);
}
