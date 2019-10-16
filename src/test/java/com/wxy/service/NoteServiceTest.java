package com.wxy.service;

import com.wxy.entity.Note;
import com.wxy.util.AESUtils;
import com.wxy.util.HtmlToTextUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
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
        Long id = 1L;
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
        String title = "";
        List<Note> list = noteService.findAll(userId, categoryId, title);
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

    /**
     * 导出全部笔记
     *
     * @throws IOException
     */
    @Test
    public void downloadAll() throws IOException {
        List<Note> all = noteService.findAll(1l, null, null);
        final StringBuilder sb = new StringBuilder();
        all.forEach(note -> {
            sb.append(String.format("// =============== %s ============= //", note.getTitle())).append("\n");
            try {
                sb.append(HtmlToTextUtils.convert(AESUtils.decrypt(note.getContent(), note.getPassword()))).append("\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        FileOutputStream fos = new FileOutputStream(String.format("download_%tF.txt", new Date()));
        fos.write(sb.toString().getBytes());
        log.info("导出全部笔记 =============");
    }
}
