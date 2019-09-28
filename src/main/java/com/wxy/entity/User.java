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
    /**
     * 账号
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 盐值
     */
    private String salt;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 头像
     */
    private String photo;
}
