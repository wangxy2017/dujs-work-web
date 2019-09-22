package com.wxy.entity;

import com.wxy.core.BaseEntity;
import lombok.Data;

/**
 * @Author wxy
 * @Date 19-7-19 下午5:42
 * @Description TODO
 **/
@Data
public class Bookmark extends BaseEntity {
    /**
     * 图标
     */
    private String icon;
    /**
     * 链接
     */
    private String href;
    /**
     * 名称
     */
    private String name;
    /**
     * 书签类型
     */
    private String type;
    /**
     * 用户id
     */
    private Long userId;
}
