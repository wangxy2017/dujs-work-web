package com.wxy.controller;

import com.wxy.entity.Note;
import com.wxy.request.NoteParam;
import com.wxy.service.NoteService;
import com.wxy.util.ApiResponse;
import com.wxy.util.TokenHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    /**
     * 保存笔记
     *
     * @param note
     * @return
     */
    @ApiOperation(value = "保存笔记", notes = "保存笔记")
    @PostMapping("/save")
    public ApiResponse save(@RequestBody NoteParam note) {
        int save = noteService.saveNote(note.getTitle(), note.getContent(), note.getCategory_id(), TokenHelper.getUserId());
        if (save > 0) {
            return ApiResponse.success();
        }
        return ApiResponse.error();
    }

    /**
     * 查询笔记
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "根据ID查询内容", notes = "根据ID查询内容")
    @GetMapping("/content/{id}")
    public ApiResponse content(@PathVariable Long id) {
        Note note = noteService.queryById(id);
        if (note != null) {
            return ApiResponse.success(note);
        }
        return ApiResponse.error(-1, "查询出错");
    }

    /**
     * 查询所有笔记
     *
     * @param category_id
     * @return
     */
    @ApiOperation(value = "查询所有笔记", notes = "查询所有笔记")
    @GetMapping("/findAll/{category_id}")
    public ApiResponse findAll(@PathVariable(required = false) Long category_id) {
        List<Note> list = noteService.findAll(TokenHelper.getUserId(), category_id == 0 ? null : category_id);
        return ApiResponse.success(list);
    }

    /**
     * 修改笔记
     *
     * @param note
     * @return
     */
    @ApiOperation(value = "修改笔记", notes = "修改笔记")
    @PostMapping("/update")
    public ApiResponse update(@RequestBody NoteParam note) {
        int i = noteService.updateNote(TokenHelper.getUserId(), note.getTitle(), note.getContent(), note.getCategory_id());
        if (i > 0) {
            return ApiResponse.success();
        }
        return ApiResponse.error();
    }

    /**
     * 删除笔记
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "删除笔记", notes = "删除笔记")
    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable Long id) {
        noteService.deleteNote(id, TokenHelper.getUserId());
        return ApiResponse.success();
    }
}
