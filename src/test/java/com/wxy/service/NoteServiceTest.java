package com.wxy.service;

import com.wxy.entity.Note;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @Author wxy
 * @Date 19-8-30 下午5:30
 * @Description TODO
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class NoteServiceTest {

    @Autowired
    private NoteService noteService;

    /**
     * 保存笔记
     */
    @Test
    public void TestSaveNote() {
        String title = "测试";
        String content = "测试";
        Long categoryId = 1L;
        Long userId = 4L;
        int save = noteService.saveNote(title, content, categoryId, userId);
        log.info("新增笔记：save = {}", save > 0);
    }

    /**
     * 修改笔记
     */
    @Test
    public void TestUpdateNote() {
        Long id = 2L;
        String title = "修改测试";
        String content = "修改测试";
        Long categoryId = 2L;
        int update = noteService.updateNote(id, title, content, categoryId);
        log.info("修改笔记：update = {}", update > 0);
    }

    /**
     * 根据ID查询
     */
    @Test
    public void TestQueryById() {
        Long id = 2L;
        Note note = noteService.queryById(id);
        log.info("查询笔记；note = {}", note);
    }

    /**
     * 查询所有笔记
     */
    @Test
    public void TestFindAll() {
        Long userId = 1L;
        Long categoryId = null;
        List<Note> list = noteService.findAll(userId, categoryId);
        log.info("查询笔记列表；list = {}", list);
    }

    /**
     * 删除笔记
     */
    @Test
    public void TestDeleteNote() {
        Long id = 1L;
        Long userId = 4L;
        boolean bool = noteService.deleteNote(id, userId);
        log.info("删除笔记；bool = {}", bool);
    }

    @Test
    public void TestFindRecycleList() {
        Long userId = 7L;
        List<Note> list = noteService.findRecycleList(userId);
        log.info("查询回收站笔记：list = {}", list);
    }
}
