package com.wxy.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wxy.entity.Bookmark;
import com.wxy.mapper.BookmarkMapper;
import com.wxy.service.BookmarkService;
import com.wxy.util.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

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
    public PageModel<Bookmark> queryPageList(Long userId, String name, Integer pageNum, Integer pageSize) {
        Assert.notNull(userId, "The parameter userId is required");
        Assert.notNull(pageNum, "The parameter pageNum is required");
        Assert.notNull(pageSize, "The parameter pageSize is required");

        PageHelper.startPage(pageNum, pageSize);

        Bookmark bookmark = new Bookmark();
        bookmark.setUserId(userId);
        bookmark.setName(name);
        Page<Bookmark> page = bookmarkMapper.queryPageList(bookmark);
        return new PageModel<>(page);
    }

    @Override
    public boolean deleteAll(Long userId) {
        Assert.notNull(userId, "The parameter userId is required");
        bookmarkMapper.deleteByUserId(userId);
        return true;
    }

    @Override
    public List<Bookmark> findAll(Long userId) {
        Assert.notNull(userId, "The parameter userId is required");
        Bookmark bookmark = new Bookmark();
        bookmark.setUserId(userId);
        List<Bookmark> list = bookmarkMapper.queryList(bookmark);
        return list;
    }
}
