package com.wxy.request;

import lombok.Data;

/**
 * @Author wxy
 * @Date 19-8-30 下午1:51
 * @Description TODO
 **/
@Data
public class NoteParam {

    private Long id;
    /**
     * 标题
     */
    private String title;
    /**
     * 内容
     */
    private String content;
    /**
     * 分类
     */
    private Long category_id;
}
