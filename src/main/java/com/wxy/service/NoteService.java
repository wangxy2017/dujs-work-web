package com.wxy.service;

import com.wxy.entity.Note;

import java.util.List;

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
    int saveNote(String title, String content, Long categoryId, Long userId);

    /**
     * 修改笔记
     *
     * @param id
     * @param title
     * @param content
     * @param categoryId
     * @return
     */
    int updateNote(Long id, String title, String content, Long categoryId);

    /**
     * 查询笔记
     *
     * @param id
     * @return
     */
    Note queryById(Long id);

    /**
     * 查询所有笔记
     *
     * @param userId
     * @param categoryId
     * @return
     */
    List<Note> findAll(Long userId, Long categoryId);

    /**
     * 删除笔记
     *
     * @param id
     */
    boolean deleteNote(Long id, Long userId);

    /**
     * 查询回收站笔记
     *
     * @return
     */
    List<Note> findRecycleList(Long userId);

    /**
     * 清空回收站
     *
     * @param userId
     * @return
     */
    boolean cleanRecycle(Long userId);
}
