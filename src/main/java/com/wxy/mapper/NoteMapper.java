package com.wxy.mapper;

import com.wxy.core.BaseMapper;
import com.wxy.entity.Note;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteMapper extends BaseMapper<Note> {
    /**
     * 放入回收站
     *
     * @param id
     * @return
     */
    int toRecycle(Long id);
}
