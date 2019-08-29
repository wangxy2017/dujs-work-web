package com.wxy.service;

import com.wxy.entity.Bookmark;

import java.util.List;

/**
 * @Author wxy
 * @Date 19-7-22 上午10:13
 * @Description TODO
 **/
public interface BookmarkService {

    int saveBookmarkByBatch(List<Bookmark> list);

    List<Bookmark> queryList(Bookmark bookmark);
}
