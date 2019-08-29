package com.wxy.entity;

import com.wxy.core.BaseEntity;
import lombok.Data;

@Data
public class Note extends BaseEntity {

    private String title;
    private String content;
    private String password;
}
