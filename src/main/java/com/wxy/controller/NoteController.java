package com.wxy.controller;

import com.wxy.entity.Note;
import com.wxy.service.NoteService;
import com.wxy.util.ApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Author wxy
 * @Date 19-7-19 下午12:15
 * @Description TODO
 **/
@Api(description = "笔记管理")
@RestController
@RequestMapping("/note")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @ApiOperation(value = "保存笔记", notes = "保存笔记")
    @PostMapping("/save")
    public ApiResponse save(@RequestBody Map<String, String> params) {
        String title = params.get("title");
        String content = params.get("content");
        int save = noteService.saveNote(title, content);
        if (save > 0) {
            return ApiResponse.success();
        }
        return ApiResponse.error();
    }

    @ApiOperation(value = "根据ID查询内容", notes = "根据ID查询内容")
    @GetMapping("/content/{id}")
    public ApiResponse content(@PathVariable Long id) {
        Note note = noteService.queryById(id);
        if (note != null) {
            return ApiResponse.success(note.getContent());
        }
        return ApiResponse.error(-1, "查询出错");
    }
}
