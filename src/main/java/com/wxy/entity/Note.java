package com.wxy.entity;

import com.wxy.core.BaseEntity;
import lombok.Data;

@Data
public class Note extends BaseEntity {

    /**
     * 标题
     */
    private String title;
    /**
     * 内容
     */
    private String content;
    /**
     * 密钥
     */
    private String password;
    /**
     * 分类ID
     */
    private Long categoryId;
    /**
     * 用户ID
     */
    private Long userId;
}
