package com.wxy.entity;

import com.wxy.core.BaseEntity;
import lombok.Data;

/**
 * @Author wxy
 * @Date 19-7-24 上午11:17
 * @Description TODO
 **/
@Data
public class Category extends BaseEntity {
    /**
     * 分类名称
     */
    private String name;
    /**
     * 用户ID
     */
    private Long userId;
}
