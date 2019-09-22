package com.wxy.service;

import com.wxy.entity.Bookmark;
import com.wxy.util.PageModel;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class BookmarkServiceTest {

    @Autowired
    private BookmarkService bookmarkService;

    /**
     * 批量插入书签
     */
    @Test
    public void TestSaveBookmarkByBatch() {
        List<Bookmark> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Bookmark bookmark = new Bookmark();
            bookmark.setIcon("icon" + i);
            bookmark.setHref("href" + i);
            bookmark.setName("name" + i);
            bookmark.setType("type" + i);
            bookmark.setUserId(4L);
            list.add(bookmark);
        }
        int save = bookmarkService.saveBookmarkByBatch(list);
        log.info("批量插入：save = {}", save);
    }

    /**
     * 分页查询
     */
    @Test
    public void TestQueryPageList() {
        Long userId = 4L;
        String name = "";
        Integer pageNum = 1;
        Integer pageSize = 2;
        PageModel<Bookmark> model = bookmarkService.queryPageList(userId, name, pageNum, pageSize);
        log.info("分页查询：page = {}", model);
    }

    /**
     * 清空书签
     */
    @Test
    public void TestDeleteAll() {
        Long userId = 4L;
        boolean bool = bookmarkService.deleteAll(userId);
        log.info("清空书签：bool = {}", bool);
    }
}
