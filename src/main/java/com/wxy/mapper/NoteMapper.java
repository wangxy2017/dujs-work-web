package com.wxy.mapper;

import com.wxy.core.BaseMapper;
import com.wxy.entity.Note;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface NoteMapper extends BaseMapper<Note> {
    /**
     * 清除回收站
     *
     * @param params
     */
    void cleanRecycle(Map<String, Object> params);
}
