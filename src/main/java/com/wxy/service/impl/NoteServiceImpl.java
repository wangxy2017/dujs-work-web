package com.wxy.service.impl;

import com.wxy.constanst.NoteCategories;
import com.wxy.entity.Category;
import com.wxy.entity.Note;
import com.wxy.mapper.NoteMapper;
import com.wxy.service.CategoryService;
import com.wxy.service.NoteService;
import com.wxy.util.AESUtils;
import com.wxy.util.CodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author wxy
 * @Date 19-7-19 上午11:57
 * @Description TODO
 **/
@Service
public class NoteServiceImpl implements NoteService {

    @Autowired
    private NoteMapper noteMapper;

    @Autowired
    private CategoryService categoryService;

    @Override
    public int saveNote(String title, String content, Long categoryId, Long userId) {
        Assert.hasText(title, "The parameter title is required");
        Assert.hasText(content, "The parameter content is required");
        Assert.notNull(categoryId, "The parameter categoryId is required");
        Assert.notNull(userId, "The parameter userId is required");
        Note note = new Note();
        note.setTitle(title);
        note.setPassword(CodeUtils.randomStr(8));
        note.setContent(AESUtils.encrypt(content, note.getPassword()));
        note.setCategoryId(categoryId);
        note.setUserId(userId);
        return noteMapper.save(note);
    }

    @Override
    public Note queryById(Long id) {
        Assert.notNull(id, "The parameter id is required");
        Note note = noteMapper.queryById(id);
        if (note != null) {
            // 解密
            note.setContent(AESUtils.decrypt(note.getContent(), note.getPassword()));
            return note;
        }
        return null;
    }

    @Override
    public List<Note> findAll(Long userId, Long categoryId) {
        Assert.notNull(userId, "The parameter userId is required");
        Note note = new Note();
        note.setUserId(userId);
        note.setCategoryId(categoryId);
        List<Note> list = noteMapper.queryList(note);
        // 过滤回收站
        Category recycle = categoryService.findRecycle(userId);
        return list.stream().filter(n -> !n.getCategoryId().equals(recycle.getId())).collect(Collectors.toList());
    }

    @Override
    public int updateNote(Long id, String title, String content, Long categoryId) {
        Assert.notNull(id, "The parameter id is required");
        Note note = noteMapper.queryById(id);
        if (note != null) {
            note.setTitle(title);
            note.setPassword(CodeUtils.randomStr(8));
            note.setContent(AESUtils.encrypt(content, note.getPassword()));
            note.setCategoryId(categoryId);
            return noteMapper.update(note);
        }
        throw new RuntimeException("查询不到笔记");
    }

    @Override
    public boolean deleteNote(Long id, Long userId) {
        Assert.notNull(id, "The parameter id is required");
        Assert.notNull(userId, "The parameter userId is required");
        Category recycle = categoryService.findRecycle(userId);
        if (recycle!=null) {
            Note note = new Note();
            note.setId(id);
            note.setCategoryId(recycle.getId());
            return noteMapper.update(note) > 0;
        }
        throw new RuntimeException("查询不到回收站");
    }

    @Override
    public List<Note> findRecycleList(Long userId) {
        Assert.notNull(userId, "The parameter userId is required");
        Category recycle = categoryService.findRecycle(userId);
        if (recycle != null) {
            Note note = new Note();
            note.setCategoryId(recycle.getId());
            note.setUserId(userId);
            return noteMapper.queryList(note);
        }
        return null;
    }

    @Override
    public boolean cleanRecycle(Long userId) {
        Assert.notNull(userId, "The parameter userId is required");
        Category recycle = categoryService.findRecycle(userId);
        if (recycle != null) {
            Map<String, Object> params = new HashMap<>();
            params.put("userId", userId);
            params.put("recycleId", recycle.getId());
            noteMapper.cleanRecycle(params);
            return true;
        }
        return false;
    }
}
