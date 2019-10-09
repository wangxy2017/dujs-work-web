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

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
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
    @PostMapping("/upload")
    public ApiResponse upload(@RequestParam("file") MultipartFile file) throws IOException {
        log.info("上传文件：file = {}", file.getOriginalFilename());
        if (file.getOriginalFilename().endsWith(".html")) {
            // 解析文件内容
            List<Bookmark> bookmarks = new ArrayList<>();
            Document document = Jsoup.parse(file.getInputStream(), "UTF-8", "");
            Elements elements = document.getElementsByTag("A");
            elements.forEach(a -> {
                Bookmark b = new Bookmark();
                b.setIcon(a.attr("ICON"));
                b.setHref(a.attr("HREF"));
                b.setName(a.text());
                b.setType("默认导入");
                b.setUserId(TokenHelper.getUserId());
                bookmarks.add(b);
            });
            // 去重保存
            List<Bookmark> all = bookmarkService.findAll(TokenHelper.getUserId());
            Iterator<Bookmark> iterator = bookmarks.iterator();
            while (iterator.hasNext()) {
                Bookmark next = iterator.next();
                all.forEach(bookmark -> {
                    if (bookmark.getHref().equals(next.getHref())) {
                        iterator.remove();
                    }
                });
            }
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

    /**
     * 导出书签
     *
     * @param response
     * @throws IOException
     */
    @ApiOperation(value = "导出书签", notes = "导出书签")
    @GetMapping("/download/{userId}")
    public void download(HttpServletResponse response, @PathVariable Long userId) throws IOException {
        final String prefix = "<!DOCTYPE NETSCAPE-Bookmark-file-1>\n" +
                "<META HTTP-EQUIV=\"Content-Type\" CONTENT=\"text/html; charset=UTF-8\">\n" +
                "<TITLE>Bookmarks</TITLE>\n" +
                "<H1>Bookmarks</H1>\n";
        List<Bookmark> list = bookmarkService.findAll(userId);
        StringBuilder webs = new StringBuilder();
        list.forEach(bookmark -> webs.append("<A HREF=\"").append(bookmark.getHref()).append("\" ADD_DATE=\"1562304736\" ICON=\"").append(bookmark.getIcon()).append("\">").append(bookmark.getName()).append("</A>\n"));
        response.setContentType("application/force-download");// 设置强制下载不打开
        response.addHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode("bookmarks_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy_MM_dd")) + ".html", StandardCharsets.UTF_8.name()));// 设置文件名
        OutputStream os = response.getOutputStream();
        os.write((prefix + webs.toString()).getBytes());
    }

    /**
     * 删除书签
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "删除书签", notes = "删除书签")
    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable Long id) {
        boolean b = bookmarkService.delete(id);
        if (b) {
            return ApiResponse.success();
        }
        return ApiResponse.error();
    }
}
