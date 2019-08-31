package com.wxy.controller;

import com.wxy.entity.Note;
import com.wxy.entity.User;
import com.wxy.request.NoteParam;
import com.wxy.service.NoteService;
import com.wxy.util.ApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
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

    /**
     * 保存笔记
     *
     * @param request
     * @param note
     * @return
     */
    @ApiOperation(value = "保存笔记", notes = "保存笔记")
    @PostMapping("/save")
    public ApiResponse save(@ApiIgnore HttpServletRequest request, @RequestBody NoteParam note) {
        User loginUser = (User) request.getSession().getAttribute("loginUser");
        if (loginUser == null) {
            throw new RuntimeException("未登录");
        }
        int save = noteService.saveNote(note.getTitle(), note.getContent(), note.getCategory_id(), loginUser.getId());
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
            return ApiResponse.success(note.getContent());
        }
        return ApiResponse.error(-1, "查询出错");
    }

    /**
     * 查询所有笔记
     *
     * @param request
     * @param category_id
     * @return
     */
    @ApiOperation(value = "查询所有笔记", notes = "查询所有笔记")
    @GetMapping("/findAll/{category_id}")
    public ApiResponse findAll(@ApiIgnore HttpServletRequest request, @PathVariable(required = false) Long category_id) {
        User loginUser = (User) request.getSession().getAttribute("loginUser");
        if (loginUser == null) {
            throw new RuntimeException("未登录");
        }
        List<Note> list = noteService.findAll(loginUser.getId(), category_id);
        return ApiResponse.success(list);
    }

    /**
     * 修改笔记
     *
     * @param request
     * @param note
     * @return
     */
    @ApiOperation(value = "修改笔记", notes = "修改笔记")
    @PostMapping("/update")
    public ApiResponse update(@ApiIgnore HttpServletRequest request, @RequestBody NoteParam note) {
        User loginUser = (User) request.getSession().getAttribute("loginUser");
        if (loginUser == null) {
            throw new RuntimeException("未登录");
        }
        int i = noteService.updateNote(loginUser.getId(), note.getTitle(), note.getContent(), note.getCategory_id());
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
    public ApiResponse delete(@ApiIgnore HttpServletRequest request, @PathVariable Long id) {
        User loginUser = (User) request.getSession().getAttribute("loginUser");
        if (loginUser == null) {
            throw new RuntimeException("未登录");
        }
        noteService.deleteNote(id, loginUser.getId());
        return ApiResponse.success();
    }
}
