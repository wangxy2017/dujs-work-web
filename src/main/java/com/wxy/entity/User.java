package com.wxy.entity;

import com.wxy.core.BaseEntity;
import lombok.Data;

/**
 * @Author wxy
 * @Date 19-7-24 下午5:17
 * @Description TODO
 **/
@Data
public class User extends BaseEntity {
    private String username;
    private String password;
    private String email;
    private String salt;
}
