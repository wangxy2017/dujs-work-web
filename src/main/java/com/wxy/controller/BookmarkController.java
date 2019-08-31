package com.wxy.controller;

import com.wxy.entity.Bookmark;
import com.wxy.service.BookmarkService;
import com.wxy.util.ApiResponse;
import com.wxy.util.PageModel;
import com.wxy.util.TokenHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author wxy
 * @Date 19-7-22 上午10:38
 * @Description TODO
 **/
@Api(description = "书签管理")
@RestController
@RequestMapping("/bookmark")
@Slf4j
public class BookmarkController {

    @Autowired
    private BookmarkService bookmarkService;

    @ApiOperation(value = "导入书签", notes = "导入书签")
    @PutMapping
    public ApiResponse upload(@RequestParam("file") MultipartFile file) throws IOException {
        log.info("上传文件：file = {}", file.getOriginalFilename());
        if (file.getOriginalFilename().endsWith(".html")) {
            // 解析文件内容
            List<Bookmark> bookmarks = new ArrayList<>();
            Document document = Jsoup.parse(file.getInputStream(), "UTF-8", "");
            Elements elements = document.getElementsByTag("A");
            elements.forEach(a -> {
                Bookmark b = new Bookmark();
                b.setIcon("123");
                b.setHref(a.attr("HREF"));
                b.setName(a.html());
                b.setUserId(TokenHelper.getUserId());
                bookmarks.add(b);
            });
            int batch = bookmarkService.saveBookmarkByBatch(bookmarks);
            log.info("导入书签：batch = {}", batch);
            return ApiResponse.success();
        }
        return ApiResponse.error();
    }

    /**
     * 查询书签列表
     *
     * @param name
     * @return
     */
    @ApiOperation(value = "查询书签列表", notes = "查询书签列表")
    @GetMapping("/list")
    public ApiResponse list(@RequestParam(required = false) String name,
                            @RequestParam Integer pageNum,
                            @RequestParam Integer pageSize) {

        PageModel<Bookmark> model = bookmarkService.queryPageList(TokenHelper.getUserId(), name, pageNum, pageSize);
        return ApiResponse.success(model);
    }

    /**
     * 清空书签
     *
     * @return
     */
    @ApiOperation(value = "清空书签", notes = "清空书签")
    @DeleteMapping("/deleteAll")
    public ApiResponse deleteAll() {
        bookmarkService.deleteAll(TokenHelper.getUserId());
        return ApiResponse.success();
    }
}
