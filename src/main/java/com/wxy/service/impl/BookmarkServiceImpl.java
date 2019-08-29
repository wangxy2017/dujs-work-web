package com.wxy.service.impl;

import com.wxy.entity.Bookmark;
import com.wxy.mapper.BookmarkMapper;
import com.wxy.service.BookmarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author wxy
 * @Date 19-7-22 上午10:16
 * @Description TODO
 **/
@Service
public class BookmarkServiceImpl implements BookmarkService {

    @Autowired
    private BookmarkMapper bookmarkMapper;

    @Override
    public int saveBookmarkByBatch(List<Bookmark> list) {
        if (list.size() > 0) {
            return bookmarkMapper.saveByBatch(list);
        }
        return -1;
    }

    @Override
    public List<Bookmark> queryList(Bookmark bookmark) {
        return bookmarkMapper.queryList(bookmark);
    }

}
