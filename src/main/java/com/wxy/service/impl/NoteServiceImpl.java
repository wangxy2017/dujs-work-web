package com.wxy.service.impl;

import com.wxy.entity.Note;
import com.wxy.mapper.NoteMapper;
import com.wxy.service.NoteService;
import com.wxy.util.AESUtils;
import com.wxy.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

/**
 * @Author wxy
 * @Date 19-7-19 上午11:57
 * @Description TODO
 **/
@Service
public class NoteServiceImpl implements NoteService {

    @Autowired
    private NoteMapper noteMapper;

    @Override
    public int saveNote(String title, String content, Long categoryId, Long userId) {
        Assert.hasText(title, "The parameter title is required");
        Assert.hasText(content, "The parameter content is required");
        Assert.isNull(userId, "The parameter userId is required");
        Note note = new Note();
        note.setTitle(title);
        note.setPassword(MD5Utils.getSalt(8));
        note.setContent(AESUtils.encrypt(content, note.getPassword()));
        note.setCategoryId(categoryId == null ? 0 : categoryId);
        note.setUserId(userId);
        return noteMapper.save(note);
    }

    @Override
    public Note queryById(Long id) {
        Assert.notNull(id, "The parameter id is required");
        Note note = noteMapper.queryById(id);
        if (note != null) {
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
        note.setCategoryId(categoryId == null ? 0 : categoryId);
        return noteMapper.queryList(note);
    }

    @Override
    public int updateNote(Long id, String title, String content, Long categoryId) {
        Assert.notNull(id, "The parameter id is required");
        Note note = queryById(id);
        if (note != null) {
            note.setTitle(title);
            note.setPassword(MD5Utils.getSalt(8));
            note.setContent(AESUtils.encrypt(content, note.getPassword()));
            note.setCategoryId(categoryId);
            return noteMapper.update(note);
        }
        throw new RuntimeException("查询出错");
    }

    @Override
    public void deleteNote(Long id) {
        noteMapper.deleteActive(id);
    }
}
