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
    private String icon;
    private String href;
    private String name;
}
