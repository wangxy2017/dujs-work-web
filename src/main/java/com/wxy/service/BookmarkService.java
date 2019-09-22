package com.wxy.service;

import com.wxy.entity.Bookmark;
import com.wxy.util.PageModel;

import java.util.List;

/**
 * @Author wxy
 * @Date 19-7-22 上午10:13
 * @Description TODO
 **/
public interface BookmarkService {

    /**
     * 批量插入书签
     *
     * @param list
     * @return
     */
    int saveBookmarkByBatch(List<Bookmark> list);

    /**
     * 分页查询书签列表
     *
     * @param userId
     * @param name
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageModel<Bookmark> queryPageList(Long userId, String name, Integer pageNum, Integer pageSize);

    /**
     * 清空书签
     *
     * @param userId
     */
    boolean deleteAll(Long userId);

    /**
     * 查询所有书签
     * @param userId
     * @return
     */
    List<Bookmark> findAll(Long userId);
}
