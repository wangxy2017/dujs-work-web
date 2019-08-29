package com.wxy.service;

import com.wxy.entity.Note;

/**
 * @Author wxy
 * @Date 19-7-19 上午11:55
 * @Description TODO
 **/
public interface NoteService {

    /**
     * 保存笔记
     *
     * @param title
     * @param content
     * @return
     */
    int saveNote(String title, String content);

    /**
     * 查询笔记
     *
     * @param id
     * @return
     */
    Note queryById(Long id);
}
